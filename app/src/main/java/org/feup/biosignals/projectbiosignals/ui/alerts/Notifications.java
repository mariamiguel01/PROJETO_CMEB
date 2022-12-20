package org.feup.biosignals.projectbiosignals.ui.alerts;

import static android.app.PendingIntent.getActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.ActivityMainBinding;

public class Notifications extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("MyTAG", "Notifications here");
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       /* Button createNotifBt = findViewById(R.id.buttonAlertTest);
        createNotifBt.setOnClickListener((view) -> (
                createNotificationChannel();));
        }

        private void createNotificationChannel() {

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "saverChannel")
                    .setSmallIcon(R.drawable.ic_outline_notifications_active_24)
                    .setContentTitle("Correct your posture")
                    .setContentText("You can do it")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            notificationManager.notify(2008, builder.build());*/
    }
}