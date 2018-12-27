package com.app.globaledge_homecontrol_app.Model;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.app.globaledge_homecontrol_app.Presenter.BleScanningPresenter;
import com.app.globaledge_homecontrol_app.Util.Constants;
import com.app.globaledge_homecontrol_app.View.BleScanningView;
import com.globlaedge.cloud.blelibrary.blelibrary.Interface.Interface.ILeScanListResult;
import com.globlaedge.cloud.blelibrary.blelibrary.Interface.Java.BleScan;

import java.util.ArrayList;

public class BLEScanningModel implements BleScanningPresenter {

    private BleScanningView mBleScanningView;
    private Activity mActivity;
    private BleScan bleLibrary;
    private ArrayList<BluetoothDevice> deviceArrayList;

    public BLEScanningModel(BleScanningView mBleScanningView , Activity activity) {
        this.mBleScanningView = mBleScanningView;
        this.mActivity = activity;
        bleLibrary = new BleScan(mActivity);
        deviceArrayList = new ArrayList<BluetoothDevice>();

    }

    @Override
    public void scanLeDevice(BluetoothAdapter mBluetoothAdapter) {
        bleLibrary. scanLeDevice(Constants.SCAN_PERIOD, new ILeScanListResult() {
            @Override
            public void onScanComplete(ArrayList<BluetoothDevice> deviceList) {
                for(int i = 0; i< deviceList.size(); i++){
                    if(deviceList.get(i).getName() != null && deviceList.get(i).getName().startsWith("Q"))
                        deviceArrayList.add(deviceList.get(i));
                    }

                if(deviceArrayList!= null)
                {
                    mBleScanningView.ScannedDevice(deviceArrayList);
                }
            }
        });

    }


}
