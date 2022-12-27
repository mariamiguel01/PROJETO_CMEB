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

import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import org.feup.biosignals.projectbiosignals.MainActivity;
import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.FragmentAlertsBinding;
import org.feup.biosignals.projectbiosignals.databinding.ActivityMainBinding;
import org.feup.biosignals.projectbiosignals.databinding.FragmentHomeBinding;
import org.feup.biosignals.projectbiosignals.ui.exercises_news.NewsFragment;


public class AlertsFragment extends Fragment {
    private FragmentAlertsBinding binding;
    MediaPlayer mediaPlayer;
    Vibrator vibrator;

    boolean Sound_boolean,Vibration_boolean;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AlertsViewModel alertsViewModel =
                new ViewModelProvider(this).get(AlertsViewModel.class);

        binding = FragmentAlertsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sound);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        Intent intent1 = new Intent(getContext(), Notifications.class);
        binding.buttonAlertTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });
    }

    public void alertSoundVibration(){
        if(Sound_boolean){
            mediaPlayer.start();
        }else{
        }
        if(Vibration_boolean){
            vibrator.vibrate(30);
        }
    }
    @Override
    public void onResume() {
        SharedPreferences getsoundsp = getActivity().getSharedPreferences("soundSwitch", Context.MODE_PRIVATE);
        SharedPreferences getvibrationsp = getActivity().getSharedPreferences("vibrationSwitch", Context.MODE_PRIVATE);
        Sound_boolean = getsoundsp.getBoolean("soundSwitch", false);
        Vibration_boolean = getvibrationsp.getBoolean("vibrationSwitch", false);

        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}