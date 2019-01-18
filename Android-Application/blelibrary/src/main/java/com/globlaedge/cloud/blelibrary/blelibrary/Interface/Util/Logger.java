package com.globlaedge.cloud.blelibrary.blelibrary.Interface.Util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by puja on 10/4/18.
 */

public class Logger {

    private static boolean isLogEnable = true;

    public static void v(String TAG, String msg) {
        if (isLogEnable) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String TAG, String msg) {
        if (isLogEnable) {
            Log.d(TAG, msg);

        }
    }

    public static void e(String TAG, String msg) {
        if (isLogEnable) {
            Log.e(TAG, msg);
        }
    }


    public static void i(String TAG, String msg) {
        if (isLogEnable) {
            Log.i(TAG, msg);

        }
    }


}
