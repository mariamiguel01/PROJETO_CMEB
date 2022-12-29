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
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

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
        setContentView(R.layout.activity_main);
        //replaceFragment(new AlertsFragment());

        /*recyclerViewAlerts = findViewById(R.id.recyclerViewAlerts);

        mAlerts.add(new classAlertItem("Title test", "Message test"));

        layoutManager = new LinearLayoutManager(this);
        recyclerViewAlerts.setLayoutManager(layoutManager);

        alertsListAdapter = new AlertsListAdapter(mAlerts, this);
        recyclerViewAlerts.setAdapter(alertsListAdapter);

        mHandler = new Handler();*/


        NotificationManager manager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL1,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is channel 1");
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel1);
        }

        // Daily alerts
        /*Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,11);
        calendar.set(Calendar.MINUTE, 31);

        Intent intentClock = new Intent(Notifications.this, NotificationReceiver.class);
        PendingIntent pendingIntentClock = PendingIntent.getBroadcast(Notifications.this, 100,intentClock, PendingIntent.FLAG_MUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntentClock);*/

        //collapsedView.setTextViewText(R.id.notification_collapsed, "Hello world!");
        //collapsedView.setOnClickPendingIntent(R.id.notification_collapsed, clickPendingIntent);

        Intent clickNotIntent = new Intent(this, MainActivity.class);
        PendingIntent clickPendingIntent = PendingIntent.getActivity(this,1,clickNotIntent,PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL1)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                //.setCustomContentView(collapsedView)
                .setContentTitle("Title")
                .setContentText("Message")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(clickPendingIntent)
                ;



        manager.notify(1, notification.build());

        Intent intentBack = new Intent(this, MainActivity.class);
        startActivity(intentBack);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.recyclerViewAlerts, fragment);
        fragmentTransaction.commit();
    }
}