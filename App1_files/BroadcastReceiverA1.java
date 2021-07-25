package com.example.app1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BroadcastReceiverA1 extends BroadcastReceiver {
    private final static String TAG = "InBroadcastReceiverA1";
    private static Intent intentA1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "in On Receive Activity One");
        /* TODO:
            -> Catch Broadcast Intents sent by A3
            -> launch Act 2 that shows the web page/webView of the
                selected TV show
        * */

        Toast.makeText(context,
                "In On Receive app1",Toast.LENGTH_SHORT).show();
        intentA1 = new Intent(context, Activity2.class);
        intentA1.putExtra("TV_SHOW", intent.getStringExtra("TV_SHOW"));
        context.startActivity(intentA1);
    }
}
