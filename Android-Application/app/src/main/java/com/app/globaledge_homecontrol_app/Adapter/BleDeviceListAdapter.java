package com.app.globaledge_homecontrol_app.Adapter;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.globaledge_homecontrol_app.Activity.DeviceDetailsActivity;
import com.app.globaledge_homecontrol_app.R;
import com.app.globaledge_homecontrol_app.Util.Constants;
import com.globlaedge.cloud.blelibrary.blelibrary.Interface.Interface.IServiceDiscovered;
import com.globlaedge.cloud.blelibrary.blelibrary.Interface.Java.ConnectBle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class BleDeviceListAdapter extends RecyclerView.Adapter<BleDeviceListAdapter.DeviceViewHolder> {


    private static final UUID CHARECTERISTIC = UUID.fromString("aaf9b671-e878-94ab-a84b-da9844897151");
    private String value = null;
    public static List<BluetoothGattService> serviceList;

    private ArrayList<BluetoothDevice> deviceList;

    private Activity mActivity;

    public interface ItemClickListener {
        void onItemClick (int position, View view);
    }

    public BleDeviceListAdapter(ArrayList<BluetoothDevice> deviceList, Activity activity) {
        this.deviceList = deviceList;
        this.mActivity = activity;
    }
    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.device_row, viewGroup, false);

        return new DeviceViewHolder(itemView);
    }

    public void clear() {
        deviceList.clear();
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder deviceViewHolder, int i) {
        final BluetoothDevice device = deviceList.get(i);

        if (device.getName() == null || device.getName().isEmpty()){
            deviceViewHolder.deviceName.setText(mActivity.getString(R.string.unknown));
        }else {
            deviceViewHolder.deviceName.setText(device.getName());
        }
        deviceViewHolder.deviceMacAddr.setText(device.getAddress());

        deviceViewHolder.setClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                new ConnectBle(mActivity, device, new IServiceDiscovered() {
                    @Override
                    public void onServiceDiscovered(List<BluetoothGattService> service) {

                        if (service != null) {
                            serviceList = service;
                            for (int i = 0; i < service.size(); i++) {
                                BluetoothGattService bluetoothGattService = service.get(i);

                                if(service.get(i).getUuid().equals(Constants.SERVICE))
                                {
                                    Intent intent = new Intent(mActivity ,DeviceDetailsActivity.class);
                                    intent.putExtra(Constants.DEVICE_NAME,device.getName());
                                    intent.putExtra(Constants.MAC_ADDRESS, device.getAddress());
                                    mActivity.startActivity(intent);
                                }


                            }

                        }
                        else {
                            Toast.makeText(mActivity, "service not found", Toast.LENGTH_SHORT).show();
                        }

                    }


                });


            }
        });

    }



    @Override
    public int getItemCount() {
        if(deviceList != null){
            return deviceList.size();
        }
        return 0;
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ItemClickListener clickListener;
        public TextView deviceName, deviceMacAddr;
        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            deviceName = itemView.findViewById(R.id.textViewDeviceName);
            deviceMacAddr =  itemView.findViewById(R.id.textViewMacAddress);
            itemView.setOnClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
