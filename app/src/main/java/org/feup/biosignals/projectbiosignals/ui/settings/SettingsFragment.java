package org.feup.biosignals.projectbiosignals.ui.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.feup.biosignals.projectbiosignals.DBHandler;
import org.feup.biosignals.projectbiosignals.DBManager;
import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.FragmentSettingsBinding;
import org.feup.biosignals.projectbiosignals.rules;
import org.feup.biosignals.projectbiosignals.ui.stats.StatsFragment;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    //private Switch sound,vibration,connection;
    ImageButton bt_cal, bt_bl;
    DBManager db;
    String back_angle = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSettings;
        settingsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Intent intent1;
        intent1 = new Intent(getActivity(), rules.class);
        final Button button3 = (Button) root.findViewById(R.id.rules);

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent1);
            }
        });
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new DBManager(getContext());

        bt_cal = view.findViewById(R.id.calibration);
        bt_bl = view.findViewById(R.id.bluetooth);

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
                back_angle = db.getPitchPB();
                Toast.makeText(getContext(),"Device calibrated",Toast.LENGTH_SHORT);
            }
        });

        if (back_angle != null) {
            Bundle result = new Bundle(); // --> Send to home
            result.putString("calibration", back_angle);
            getParentFragmentManager().setFragmentResult("requestCalibration", result);
            Log.i("cal", back_angle);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}