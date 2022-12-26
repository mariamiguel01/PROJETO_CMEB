package org.feup.biosignals.projectbiosignals;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;

public class Settings extends AppCompatActivity {
    private Switch sound,vibration,connection;
    ImageButton bt_cal;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = (Switch)findViewById(R.id.soundSwitch);
        vibration = (Switch)findViewById(R.id.vibrationSwitch);
        connection = (Switch)findViewById(R.id.switch3);
        bt_cal = (ImageButton)findViewById(R.id.calibration);

        SharedPreferences getsoundsp = getSharedPreferences("soundSwitch", MODE_PRIVATE);
        SharedPreferences getvibrationsp = getSharedPreferences("vibrationSwitch", MODE_PRIVATE);
        sound.setChecked(getsoundsp.getBoolean("soundSwitch", false));
        vibration.setChecked(getvibrationsp.getBoolean("vibrationSwitch", false));

        bt_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Here goes the code that saves the current data as the "zero"
            }
        });

        public void SoundSwitch(View view) {
            if(sound.isChecked()){
                SharedPreferences.Editor soundswitchPreference = getSharedPreferences("soundSwitch", MODE_PRIVATE).edit();
                soundswitchPreference.putBoolean("soundSwitch", true);
                soundswitchPreference.apply();
                sound.setChecked(true);
            }else{
                SharedPreferences.Editor soundswitchPreference = getSharedPreferences("soundSwitch", MODE_PRIVATE).edit();
                soundswitchPreference.putBoolean("soundSwitch", false);
                soundswitchPreference.apply();
                sound.setChecked(false);
            }
        }


        public void VibrationSwitch(View view) {
            if(vibration.isChecked()){
                SharedPreferences.Editor vibrationswitchPreference = getSharedPreferences("vibrationSwitch", MODE_PRIVATE).edit();
                vibrationswitchPreference.putBoolean("vibrationSwitch", true);
                vibrationswitchPreference.apply();
                vibration.setChecked(true);
            }else{
                SharedPreferences.Editor vibrationswitchPreference = getSharedPreferences("vibrationSwitch", MODE_PRIVATE).edit();
                vibrationswitchPreference.putBoolean("vibrationSwitch", false);
                vibrationswitchPreference.apply();
                vibration.setChecked(false);
            }
        }
    }
}