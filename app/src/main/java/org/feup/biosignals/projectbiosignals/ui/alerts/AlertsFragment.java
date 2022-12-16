package org.feup.biosignals.projectbiosignals.ui.alerts;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.feup.biosignals.projectbiosignals.MainActivity;
import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.FragmentAlertsBinding;
import org.feup.biosignals.projectbiosignals.databinding.ActivityMainBinding;
import org.feup.biosignals.projectbiosignals.databinding.FragmentHomeBinding;


public class AlertsFragment extends Fragment {
    private FragmentAlertsBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AlertsViewModel alertsViewModel =
                new ViewModelProvider(this).get(AlertsViewModel.class);

        binding = FragmentAlertsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       /* NotificationManager notificationManager = ( NotificationManager ) getActivity().getSystemService( getActivity().NOTIFICATION_SERVICE );
        int icon = R.drawable.ic_outline_notifications_active_24;
        CharSequence tickerText = "your daily tip";
        long when = System.currentTimeMillis();

        Context context = getActivity();
        CharSequence contentTitle = "AutoKit";
        CharSequence contentText = "hi";
        Intent notificationIntent = new Intent();
        PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 0, notificationIntent, 0);
        Notification mnotification = new Notification(icon, tickerText, when);


        notificationManager.notify(2008, mnotification);*/

        /*mnotification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
        NotificationManager notificationManager;
        notificationManager.notify(1, mnotification);*/

        final TextView textView = binding.textDashboard;
        alertsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}