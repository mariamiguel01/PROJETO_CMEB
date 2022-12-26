package org.feup.biosignals.projectbiosignals.ui.settings;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.helpers.DataParser;
import org.feup.biosignals.projectbiosignals.video;

import java.util.Arrays;

public class bleConnection extends AppCompatActivity {

    private final String TAG = "AcquisitionActivity";
    private static final int REQUEST_CONNECTION = 2;


    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if ("DATA_AVAILABLE".equals(action)) {
                byte[] data = intent.getByteArrayExtra("data");
                //byte[] data = string.getBytes(StandardCharsets.UTF_8);
                Log.i(TAG,"Data Received");
                Log.i(TAG, Arrays.toString(data));
                DataParser dataParser = new DataParser();
                dataParser.parse(data);
                if (data!=null) {
                    for (int i = 0; i < 5; i++) {
                        float accX = dataParser.getAccDataX().get(i);
                        float accY = dataParser.getAccDataY().get(i);
                        float accZ = dataParser.getAccDataZ().get(i);
                        float gyroX = dataParser.getGyroDataX().get(i);
                        float gyroY = dataParser.getGyroDataY().get(i);
                        float gyroZ = dataParser.getGyroDataZ().get(i);
                        float magX = dataParser.getMagDataX().get(i);
                        float magY = dataParser.getMagDataY().get(i);
                        float magZ = dataParser.getMagDataZ().get(i);
                        float forceOne = dataParser.getForceOneData().get(i);
                        float forceTwo = dataParser.getForceOneData().get(i);
                        Log.i(TAG, "" + gyroX);
                    }
                }
            }
        }
    };

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("DATA_AVAILABLE");

        return intentFilter;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ble);
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());

        /*Intent intent2connection = new Intent(this, ConnectionActivity.class);
        Button button2connect = findViewById(R.id.button);
        button2connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent2connection);
            }
        });*/
    }

/*    public void connect(){
        // Function on XML onClick
        Log.i(TAG, "Clicked button");

        Intent intent = new Intent(this, ConnectionActivity.class);
        startActivityForResult(intent, REQUEST_CONNECTION);
    }*/

    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i(TAG,data.getStringExtra("address"));
        Intent serviceIntent = new Intent(this, DataStreamService.class);
        serviceIntent.putExtra("address", data.getStringExtra("address"));
        startForegroundService(serviceIntent);

        super.onActivityResult(requestCode, resultCode, data);
    }
}
