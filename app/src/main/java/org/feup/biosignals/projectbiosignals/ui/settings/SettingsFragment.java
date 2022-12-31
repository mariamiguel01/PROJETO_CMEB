package org.feup.biosignals.projectbiosignals.ui.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.feup.biosignals.projectbiosignals.DBHandler;
import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.FragmentSettingsBinding;
import org.feup.biosignals.projectbiosignals.ui.stats.StatsFragment;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    //private Switch sound,vibration,connection;
    ImageButton bt_cal, bt_bl;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSettings;
        settingsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

/*        sound = view.findViewById(R.id.soundSwitch);
        vibration = view.findViewById(R.id.vibrationSwitch);*/
        bt_cal = view.findViewById(R.id.calibration);
        bt_bl = view.findViewById(R.id.bluetooth);

/*        SharedPreferences getsoundsp = getActivity().getSharedPreferences("soundSwitch", Context.MODE_PRIVATE);
        SharedPreferences getvibrationsp = getActivity().getSharedPreferences("vibrationSwitch", Context.MODE_PRIVATE);
        sound.setChecked(getsoundsp.getBoolean("soundSwitch", false));
        vibration.setChecked(getvibrationsp.getBoolean("vibrationSwitch", false));*/
/*
        SoundSwitch();
        VibrationSwitch();*/

        bt_bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sends to
                Intent intent_to_ble = new Intent(getContext(), bleConnection.class);
                startActivity(intent_to_ble);
            }
        });

        bt_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // the idea is to somehow save the current values, and store the Euler Angles to be the zero, subtracting these values for each calculated angle
            }
        });
    }


/*    public void SoundSwitch() {
        if(sound.isChecked()){
            SharedPreferences.Editor soundswitchPreference = getActivity().getSharedPreferences("soundSwitch", Context.MODE_PRIVATE).edit();
            soundswitchPreference.putBoolean("soundSwitch", true);
            soundswitchPreference.apply();
            sound.setChecked(true);
        }else{
            SharedPreferences.Editor soundswitchPreference = getActivity().getSharedPreferences("soundSwitch", Context.MODE_PRIVATE).edit();
            soundswitchPreference.putBoolean("soundSwitch", false);
            soundswitchPreference.apply();
            sound.setChecked(false);
        }
    }

    public void VibrationSwitch() {
        if(vibration.isChecked()){
            SharedPreferences.Editor vibrationswitchPreference = getActivity().getSharedPreferences("vibrationSwitch", Context.MODE_PRIVATE).edit();
            vibrationswitchPreference.putBoolean("vibrationSwitch", true);
            vibrationswitchPreference.apply();
            vibration.setChecked(true);
        }else{
            SharedPreferences.Editor vibrationswitchPreference = getActivity().getSharedPreferences("vibrationSwitch", Context.MODE_PRIVATE).edit();
            vibrationswitchPreference.putBoolean("vibrationSwitch", false);
            vibrationswitchPreference.apply();
            vibration.setChecked(false);
        }
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}