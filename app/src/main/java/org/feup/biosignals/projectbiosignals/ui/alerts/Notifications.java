package org.feup.biosignals.projectbiosignals.ui.alerts;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.ActivityMainBinding;

public class Notifications extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "saverChannel")
            .setSmallIcon(R.drawable.ic_outline_notifications_active_24)
            .setContentTitle("Correct your posture")
            .setContentText("You can do it")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2008, builder.build());

    }
}
