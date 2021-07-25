package com.example.app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

public class BroadcastReceiverA2 extends BroadcastReceiver {

    private final static String TAG = "InBroadcastReceiverA2";
    private static final String TOAST_INTENT = "TV_SHOW";
    private BroadcastReceiver receiver_A3;
    private IntentFilter mFilterA2;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "in On Receive in app2 Main activity");
        Toast.makeText(context,
                "In On Receive app2",Toast.LENGTH_SHORT).show();
    }
}
