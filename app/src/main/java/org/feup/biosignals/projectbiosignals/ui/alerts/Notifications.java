/*
package org.feup.biosignals.projectbiosignals.ui.alerts;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import org.feup.biosignals.projectbiosignals.MainActivity;
import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.adapters.LeDeviceListAdapter;
import org.feup.biosignals.projectbiosignals.databinding.ActivityMainBinding;
import org.feup.biosignals.projectbiosignals.ui.exercises_news.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import java.util.Calendar;

public class Notifications extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final String CHANNEL1 = "channel1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_alerts_list);
        //replaceFragment(new AlertsFragment());

        */
/*recyclerViewAlerts = findViewById(R.id.recyclerViewAlerts);

        mAlerts.add(new classAlertItem("Title test", "Message test"));

        layoutManager = new LinearLayoutManager(this);
        recyclerViewAlerts.setLayoutManager(layoutManager);

        alertsListAdapter = new AlertsListAdapter(mAlerts, this);
        recyclerViewAlerts.setAdapter(alertsListAdapter);

        mHandler = new Handler();*//*



        // Daily alerts --> Apagar depois
        */
/*Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,11);
        calendar.set(Calendar.MINUTE, 31);
        Intent intentClock = new Intent(Notifications.this, NotificationReceiver.class);
        PendingIntent pendingIntentClock = PendingIntent.getBroadcast(Notifications.this, 100,intentClock, PendingIntent.FLAG_MUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntentClock);*//*


        //collapsedView.setTextViewText(R.id.notification_collapsed, "Hello world!");
        //collapsedView.setOnClickPendingIntent(R.id.notification_collapsed, clickPendingIntent);

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.recyclerViewAlerts, fragment);
        fragmentTransaction.commit();
    }
}*/
