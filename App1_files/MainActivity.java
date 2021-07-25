package com.example.app1;
/*
* Name: Varun Subramanian
* NetID: vsubra25
* CS 478
* App1
*/

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity_app1";
    private static final String TOAST_INTENT = "TV_SHOW";
    private static final String DANGEROUS_PERMISSION = "edu.uic.cs478.s19.kaboom";
    private Button button;
    private BroadcastReceiver receiver_A3;
    private IntentFilter mFilterA1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        receiver_A3 = new BroadcastReceiverA1();
//        mFilterA1 = new IntentFilter(TOAST_INTENT);
//        mFilterA1.setPriority(1);
//        registerReceiver(receiver_A3, mFilterA1);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionAndBroadcast();
                //TODO: start Main Activity in App2
            }
        });
    }

    /* TODO: checkPermissionAndBroadcast
        if(MainAct A1 has permission)
        {
            Create and Register BroadcastReceiver
            Subsequently A1 launch Main Activity in A2. in onclick of button.
        }
        else { Request User to grant Permission }
    * */
    public void checkPermissionAndBroadcast() {
        if (ActivityCompat.checkSelfPermission(this, DANGEROUS_PERMISSION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i("Result of CheckPermissionAndBroadcast", "Permission Granted");
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
                receiver_A3 = new BroadcastReceiverA1();
                mFilterA1 = new IntentFilter(TOAST_INTENT);
                mFilterA1.setPriority(1);
                registerReceiver(receiver_A3, mFilterA1);
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

    @Override
    protected void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");

        if (ActivityCompat.checkSelfPermission(this, DANGEROUS_PERMISSION)
                == PackageManager.PERMISSION_GRANTED)
        {
        receiver_A3 = new BroadcastReceiverA1();
        mFilterA1 = new IntentFilter(TOAST_INTENT);
        mFilterA1.setPriority(1);
        registerReceiver(receiver_A3, mFilterA1);
            Toast.makeText(getApplicationContext(),
                    "No permission granted yet in on Resume",Toast.LENGTH_SHORT).show();
//            checkPermissionAndBroadcast();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver_A3);
    }


    /*Callbacks for debugging purposes*/

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