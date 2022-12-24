package org.feup.biosignals.projectbiosignals.ui.alerts;

import static android.app.PendingIntent.getActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDeepLinkBuilder;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import org.feup.biosignals.projectbiosignals.MainActivity;
import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.ActivityMainBinding;

import java.util.Calendar;

public class Notifications extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final String CHANNEL1 = "channel1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

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

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE, 26);
        Intent intentClock = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntentClock = PendingIntent.getBroadcast(getApplicationContext(), 100,intentClock, PendingIntent.FLAG_MUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntentClock);
        /*long timeInitial = System.currentTimeMillis();
        long tenSeconds = 1000 * 10;
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                timeInitial + tenSeconds,
                clickPendingIntent);*/

        //collapsedView.setTextViewText(R.id.notification_collapsed, "Hello world!");
        //collapsedView.setOnClickPendingIntent(R.id.notification_collapsed, clickPendingIntent);

        /*Intent clickNotIntent = new Intent(this, MainActivity.class);
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
        startActivity(intentBack);*/
    }
}