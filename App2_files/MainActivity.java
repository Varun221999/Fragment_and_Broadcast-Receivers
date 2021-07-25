package com.example.app2;
/*
* Name: Varun Subramanian
* NetID: vsubra25
* CS 478
* App2
*/
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver receiver_A3;
    private IntentFilter mFilterA2;
    private static final String TOAST_INTENT = "TV_SHOW";
    private static final String DANGEROUS_PERMISSION = "edu.uic.cs478.s19.kaboom";
    private static final String TAG = "MainActivityApp2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        receiver_A3 = new BroadcastReceiverA2();
//        mFilterA2 = new IntentFilter(TOAST_INTENT);
//        mFilterA2.setPriority(2);
//        registerReceiver(receiver_A3, mFilterA2);

        checkPermissionAndBroadcast();
    }

    /* TODO: checkPermissionAndBroadcast
        if(MainAct A2 has permission)
        {
            Create and Register BroadcastReceiver
        }
        else { Request User to grant Permission }
    * */
    public void checkPermissionAndBroadcast() {
        if (ActivityCompat.checkSelfPermission(this, DANGEROUS_PERMISSION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i("Result of CheckPermissionAndBroadcast", "Permission Granted");

            receiver_A3 = new BroadcastReceiverA2();
            mFilterA2 = new IntentFilter(TOAST_INTENT);
            mFilterA2.setPriority(2);
            registerReceiver(receiver_A3, mFilterA2);
            //Intent aIntent = new Intent(TOAST_INTENT) ;
            //sendOrderedBroadcast(aIntent, DANGEROUS_PERMISSION) ;
        }
        else {
            Log.i("Result of CheckPermissionAndBroadcast", "Permission Not Granted");
            ActivityCompat.requestPermissions(this, new String[]{DANGEROUS_PERMISSION}, 0);
        }
    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
            /* TODO: Create and Register BroadcastReceiver
                  Subsequently A1 launch Main Activity in A2.
            */
                Log.i("Result of onRequestPermissionResult", "Permission Granted");

                receiver_A3 = new BroadcastReceiverA2();
                mFilterA2 = new IntentFilter(TOAST_INTENT);
                mFilterA2.setPriority(2);
                registerReceiver(receiver_A3, mFilterA2);
                //Intent aIntent = new Intent(TOAST_INTENT) ;
                //sendOrderedBroadcast(aIntent, DANGEROUS_PERMISSION) ;
            }
            else {
                Toast.makeText(this, "Bummer: No permission", Toast.LENGTH_SHORT)
                        .show();
                //checkPermissionAndBroadcast();//Ask to grant the permission again?
            }
        }
    }

    /*Callbacks for debugging purposes*/
    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
        unregisterReceiver(receiver_A3);
    }

    @Override
    protected void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

}