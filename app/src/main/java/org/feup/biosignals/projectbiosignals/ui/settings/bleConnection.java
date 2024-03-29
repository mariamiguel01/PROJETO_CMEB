package org.feup.biosignals.projectbiosignals.ui.settings;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.feup.biosignals.projectbiosignals.DBHandler;
import org.feup.biosignals.projectbiosignals.DBManager;
import org.feup.biosignals.projectbiosignals.MainActivity;
import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.FragmentSettingsBinding;
import org.feup.biosignals.projectbiosignals.helpers.DataParser;
import org.feup.biosignals.projectbiosignals.ui.home.HomeFragment;
import org.feup.biosignals.projectbiosignals.ui.home.HomeViewModel;
import org.feup.biosignals.projectbiosignals.ui.stats.StatsFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class bleConnection extends AppCompatActivity {

    private final String TAG = "AcquisitionActivity";
    private static final int REQUEST_CONNECTION = 2;
    DBManager db;
    private static final double DT = 0.02;  // time step for integration
    private double pitch, roll, yaw;
    private double first_pitch, first_roll, first_yaw;
    SettingsFragment settingsFragment = new SettingsFragment();
    private boolean is_Calibrated;
    private boolean isFirst = true;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ble);

        Intent intent = getIntent();
        is_Calibrated = intent.getBooleanExtra("CALIBRATION", false);

        db = new DBManager(this);
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());

        ArrayList<String> permsList = new ArrayList<String>();
        permsList.add(Manifest.permission.BLUETOOTH);
        permsList.add(Manifest.permission.BLUETOOTH_ADMIN);
        permsList.add(Manifest.permission.BLUETOOTH_SCAN);
        permsList.add(Manifest.permission.BLUETOOTH_ADVERTISE);
        permsList.add(Manifest.permission.BLUETOOTH_CONNECT);
        permsList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permsList.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        if (ContextCompat.checkSelfPermission(this, permsList.get(0))
                != PackageManager.PERMISSION_GRANTED){
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), isGranted -> {
                Log.i(TAG, isGranted.toString());
            }).launch(permsList.stream().toArray(String[]::new));
        }
    }

    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            //is_Calibrated = getIsCal(intent);
            Log.i("bt ble con", ""+is_Calibrated);

            if ("DATA_AVAILABLE".equals(action)) {
                byte[] data = intent.getByteArrayExtra("data");
                //byte[] data = string.getBytes(StandardCharsets.UTF_8);
                Log.i(TAG, "Data Received");
                Log.i(TAG, Arrays.toString(data));
                DataParser dataParser = new DataParser();
                dataParser.parse(data);
                if (data != null) {
                    for (int i = 0; i < 5; i++) {
                        /*float accX = dataParser.getAccDataX().get(i);
                        float accY = dataParser.getAccDataY().get(i);
                        float accZ = dataParser.getAccDataZ().get(i);*/
                        float gyroX = dataParser.getGyroDataX().get(i);
                        float gyroY = dataParser.getGyroDataY().get(i);
                        float gyroZ = dataParser.getGyroDataZ().get(i);
                        /*float magX = dataParser.getMagDataX().get(i);
                        float magY = dataParser.getMagDataY().get(i);
                        float magZ = dataParser.getMagDataZ().get(i);
                        float forceOne = dataParser.getForceOneData().get(i);
                        float forceTwo = dataParser.getForceOneData().get(i);*/
                        Log.i(TAG, "" + gyroX);

                        Log.i("CAL", "" + is_Calibrated);
                        if(is_Calibrated && isFirst){
                            pitch = pitch + gyroX * DT;
                            pitch = Math.round(pitch);
                            roll = roll + gyroY * DT;
                            roll = Math.round(roll);
                            yaw += gyroZ * DT;
                            yaw = Math.round(yaw);

                            first_pitch = pitch;
                            first_roll = roll;
                            first_yaw = yaw;
                            db.AddAngle(0.0, 0.0, 0.0);
                            isFirst = false;
                        }

                        if(is_Calibrated && !isFirst){
                            pitch = (pitch + gyroX * DT) - first_pitch;
                            pitch = Math.round(pitch);
                            roll = (roll + gyroY * DT) - first_roll;
                            roll = Math.round(roll);
                            yaw += (gyroZ * DT) - first_yaw;
                            yaw = Math.round(yaw);

                            db.AddAngle(pitch, roll, yaw);
                        }
                        // this is the value of the angles without subtracting the zero
                        // the goal is to send them to the HomeFragment directly to show on the progress bar
                        //Then save the data in the database (AddAngle function for that)
                    }
                }
            }
        }
    };

    /*private boolean isCalibrated() {
        if(getIntent().hasExtra("CALIBRATION")) {
            Intent intentC = getIntent();
            //get the attached extras from the intent
            //we should use the same key as we used to attach the data.
            boolean calibration = intentC.getBooleanExtra("CALIBRATION", false);
            return calibration;
        }
        return false;
    }*/

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("DATA_AVAILABLE");
        return intentFilter;
    }

   /* @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ble);

        Intent intent = getIntent();
        is_Calibrated = intent.getBooleanExtra("CALIBRATION", false);

        db = new DBManager(this);
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());

        ArrayList<String> permsList = new ArrayList<String>();
        permsList.add(Manifest.permission.BLUETOOTH);
        permsList.add(Manifest.permission.BLUETOOTH_ADMIN);
        permsList.add(Manifest.permission.BLUETOOTH_SCAN);
        permsList.add(Manifest.permission.BLUETOOTH_ADVERTISE);
        permsList.add(Manifest.permission.BLUETOOTH_CONNECT);
        permsList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permsList.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        if (ContextCompat.checkSelfPermission(this, permsList.get(0))
                != PackageManager.PERMISSION_GRANTED){
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), isGranted -> {
                Log.i(TAG, isGranted.toString());
            }).launch(permsList.stream().toArray(String[]::new));
        }
    }*/

    public void connect(View view) {
        // Function on XML onClick
        Log.i(TAG, "Clicked button");
        Intent intent = new Intent(this, ConnectionActivity.class);
        startActivityForResult(intent, REQUEST_CONNECTION);
    }

    public void return_main(View view) {
        Log.i(TAG, "Clicked button");
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }

    public Boolean getIsCal(Intent intent) {
        is_Calibrated = intent.getBooleanExtra("CALIBRATION", false);
        Log.i("bt ble con", ""+is_Calibrated);
        return is_Calibrated;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.i(TAG, data.getStringExtra("address"));
        Intent serviceIntent = new Intent(this, DataStreamService.class);
        serviceIntent.putExtra("address", data.getStringExtra("address"));
        startForegroundService(serviceIntent);

        super.onActivityResult(requestCode, resultCode, data);
    }
}
