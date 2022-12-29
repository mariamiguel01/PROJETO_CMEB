package org.feup.biosignals.projectbiosignals.ui.alerts;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import 	android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Vibrator;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.feup.biosignals.projectbiosignals.MainActivity;
import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.FragmentAlertsBinding;
import org.feup.biosignals.projectbiosignals.databinding.ActivityMainBinding;
import org.feup.biosignals.projectbiosignals.databinding.FragmentHomeBinding;
import org.feup.biosignals.projectbiosignals.ui.exercises_news.NewsFragment;
import org.feup.biosignals.projectbiosignals.ui.settings.SettingsViewModel;

import java.util.ArrayList;


public class AlertsFragment extends Fragment implements AlertsListAdapter.OnItemListener {
    //private FragmentAlertsBinding binding;
    //MediaPlayer mediaPlayer;
    //Vibrator vibrator;
    private View root;

    private ArrayList<classAlertItem> mAlerts = new ArrayList<classAlertItem>();
    private classAlertItem mAlert;
    private AlertsListAdapter.OnItemListener mOnItemListener;
    private static final long SCAN_PERIOD = 10000;

    private AlertsListAdapter alertsListAdapter;
    private RecyclerView recyclerview;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerViewAlerts;

    private Handler mHandler;

    //boolean Sound_boolean,Vibration_boolean;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AlertsViewModel alertsViewModel =
                new ViewModelProvider(this).get(AlertsViewModel.class);

        root = inflater.inflate(R.layout.fragment_alerts_list, container, false);

        /*recyclerview = view.findViewById(R.id.recyclerViewAlerts);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        AlertsListAdapter alertsListAdapter = new AlertsListAdapter(mAlerts, mOnItemListener);
        recyclerViewAlerts.setAdapter(alertsListAdapter);*/

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //recyclerViewAlerts = recyclerViewAlerts.findViewById(R.id.recyclerViewAlerts);

        //mAlerts.add(new classAlertItem("Title test", "Message test"));

        //mHandler = new Handler();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        root = null;
    }

    @Override
    public void onItemClicked(int position) {

    }
}