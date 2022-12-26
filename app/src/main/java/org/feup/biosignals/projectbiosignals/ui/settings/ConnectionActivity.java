package org.feup.biosignals.projectbiosignals.ui.settings;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.adapters.LeDeviceListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConnectionActivity extends AppCompatActivity implements LeDeviceListAdapter.OnItemListener {

    private final String TAG = "ConnectionActivity";

    private ArrayList<BluetoothDevice> mLeDevices = new ArrayList<BluetoothDevice>();

    private BluetoothAdapter mBluetoothAdapter;
    private static final long SCAN_PERIOD = 10000;

    private LeDeviceListAdapter leDeviceListAdapter;
    RecyclerView.LayoutManager layoutManager;
    BluetoothLeScanner mBluetoothScanner;

    RecyclerView recyclerView;

    private Handler mHandler;
    private boolean mScanning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "hello");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        leDeviceListAdapter = new LeDeviceListAdapter(mLeDevices, this::onItemClicked);
        recyclerView.setAdapter(leDeviceListAdapter);

        mHandler = new Handler();
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        mBluetoothScanner = mBluetoothAdapter.getBluetoothLeScanner();
        scanDevice(true);
    }

    @SuppressLint("MissingPermission")
    private void scanDevice(final boolean enable) {
        if (enable) {
            mHandler.postDelayed(new Runnable() {
                @SuppressLint("MissingPermission")
                @Override
                public void run() {
                    mScanning = false;
                    Log.i(TAG, "Stop Scan");
                    mBluetoothScanner.stopScan(mScanCallback);

                }
            }, SCAN_PERIOD);

            mScanning = true;
            Log.i(TAG, "Start Scan");
            mBluetoothScanner.startScan(mScanCallback);
            Log.i(TAG, "Scan ok");
        } else {
            mScanning = false;
            Log.i(TAG, "Stop Scan");
            mBluetoothScanner.startScan(mScanCallback);
        }
    }

    private ScanCallback mScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            Log.i(TAG, "----");
            Log.i(TAG, "Found Device");
            @SuppressLint("MissingPermission") String deviceName = result.getDevice().getName();
            if (deviceName == null || deviceName.length() < 0) {
                deviceName = "Unknown Device";
            }
            Log.i(TAG, deviceName);
            Log.i(TAG, result.getDevice().getAddress());
            Log.i(TAG, "----");
            leDeviceListAdapter.addDevice(result.getDevice());
            leDeviceListAdapter.notifyDataSetChanged();

        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            Log.i(TAG, "Batch Results");
            for (ScanResult sr : results) {
                Log.i(TAG, "ScanResult - Results" + sr.toString());
            }

        }

        @Override
        public void onScanFailed(int errorCode) {
            Log.i(TAG, "FAILED SCAN" + errorCode);
        }
    };

    @SuppressLint("MissingPermission")
    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent();
        intent.putExtra("name", mLeDevices.get(position).getName());
        intent.putExtra("address", mLeDevices.get(position).getAddress());
        setResult(1, intent);
        finish();

    }
}