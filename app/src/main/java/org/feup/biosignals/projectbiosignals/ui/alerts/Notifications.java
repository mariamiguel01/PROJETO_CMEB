package org.feup.biosignals.projectbiosignals.ui.alerts;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.ActivityMainBinding;

public class Notifications extends AppCompatActivity {
    private ActivityMainBinding binding;

    public View onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //createNotificationChannel();

        //Button buttonShowNotification = findViewById(R.id.show);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "saverChannel")
        .setSmallIcon(R.drawable.ic_outline_notifications_active_24)
        .setContentTitle("Correct your posture")
        .setContentText("You can do it")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2008, builder.build());

        /*buttonShowNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/


        /*private void createNotificationChannel() {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationChannel mChannel = new NotificationChannel("saverChannel", "alertChannel", NotificationManager.IMPORTANCE_DEFAULT);
                mChannel.setLightColor(Color.GREEN);
                NotificationManager nm = getSystemService(NotificationManager.class);
                nm.createNotificationChannel(mChannel);
            }
        }*/


        return null;
    }
}
