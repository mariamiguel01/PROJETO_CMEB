package org.feup.biosignals.projectbiosignals.ui.alerts;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.feup.biosignals.projectbiosignals.MainActivity;
import org.feup.biosignals.projectbiosignals.R;

public class NotificationReceiver extends BroadcastReceiver {

    private static final String CHANNEL1 = "channel1";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Broadcast", "In broadcast");
        Intent intentOnClick = new Intent(context, MainActivity.class);
        intentOnClick.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntentOnClick = PendingIntent.getActivity(context,100,intentOnClick, PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, CHANNEL1)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                //.setCustomContentView(collapsedView)
                .setContentTitle("Title daily")
                .setContentText("Message daily")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntentOnClick)
                .setChannelId("channel1")
                ;

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(100,notification.build());
    }
}
