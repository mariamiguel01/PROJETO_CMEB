package org.feup.biosignals.projectbiosignals.ui.home;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import org.feup.biosignals.projectbiosignals.DBAlertsManager;
import org.feup.biosignals.projectbiosignals.DBManager;
import org.feup.biosignals.projectbiosignals.DatabaseALertsHelper;
import org.feup.biosignals.projectbiosignals.MainActivity;
import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.FragmentHomeBinding;
import org.feup.biosignals.projectbiosignals.ui.alerts.NotificationReceiver;
import org.feup.biosignals.projectbiosignals.video;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ProgressBar progressBar;
    private TextView progressText;
    private TextView pointsText;
    private TextView levelText;
    private ImageView imageToLoad;

    final Handler handler = new Handler();
    DBManager db_home;
    String back_angle;
    int i = 0;
    int points;
    String calibrationAngle;

    int timeCounter = 0;
    DBAlertsManager dbA;
    private static final String DATABASE_NAME="dbAlerts";
    public static final String TITLE="Correct your posture";
    public String MESSAGE;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override // --> Receber a informação
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db_home = new DBManager(getContext());
        dbA = new DBAlertsManager(getContext());

        /*getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String result = bundle.getString("bundleKey");
            }
        });*/

        getParentFragmentManager().setFragmentResultListener("requestCalibration", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String calibration_Angle, @NonNull Bundle bundle) {
                calibrationAngle = bundle.getString("calibration");
                Log.i("cal_home", calibrationAngle);
            }
        });

        getParentFragmentManager().setFragmentResultListener("changedPoints", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String changedPoints, @NonNull Bundle bundle) {
                points = bundle.getInt("points");
            }
        });

        getParentFragmentManager().setFragmentResultListener("statsPoints", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String statsPoints, @NonNull Bundle bundle) {
                points = bundle.getInt("points");
            }
        });
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progress_bar);
        progressText = view.findViewById(R.id.progress_text);

        levelText = view.findViewById(R.id.level);
        pointsText = view.findViewById(R.id.points_text);
        imageToLoad = view.findViewById(R.id.image_level);

        handler.postDelayed(angleRunnable, 1000);
    }

    private Runnable angleRunnable = new Runnable() {
        @Override
        public void run() {
            // set the limitations for the numeric
            // text under the progress bar
            try{
                // back_angle = (CharSequence) db_home.getListByDate().get(1);
                Log.i("angle raw", db_home.getPitchPB());
                //back_angle = Integer.toString(Integer.parseInt(db_home.getPitchPB()) - Integer.parseInt(calibrationAngle));
                back_angle = db_home.getPitchPB();
                progressText.setText(back_angle);
                progressBar.setProgress(50);
            }
            catch(Exception e){
                back_angle="---";
                progressText.setText(back_angle);
                progressBar.setProgress(0);
            }

            pointsText.setText("Points: " + Integer.toString(points));
            if (points < 5) {
                imageToLoad.setImageResource(R.drawable.level1);
                levelText.setText("Level 1 (of 6)");
            }
            if (points >= 5 && points < 10) {
                imageToLoad.setImageResource(R.drawable.level2);
                levelText.setText("Level 2 (of 6)");
            }
            if (points >= 10 && points < 15) {
                imageToLoad.setImageResource(R.drawable.level3);
                levelText.setText("Level 3 (of 6)");
            }
            if (points >= 15 && points < 20) {
                imageToLoad.setImageResource(R.drawable.level4);
                levelText.setText("Level 4 (of 6)");
            }
            if (points >= 20 && points < 25) {
                imageToLoad.setImageResource(R.drawable.level5);
                levelText.setText("Level 5 (of 6)");
            }
            if (points >= 25) {
                imageToLoad.setImageResource(R.drawable.level6);
                levelText.setText("Level 6 (of 6)");
            }

            Bundle result = new Bundle(); // --> Enviar a informação
            result.putInt("points", points);
            getParentFragmentManager().setFragmentResult("requestPoints", result);

            if (true) {
                timeCounter++;
                if (timeCounter > 10) {
                    Intent intent2notifications = new Intent(getContext(), NotificationReceiver.class);
                    getActivity().sendBroadcast(intent2notifications);
                    timeCounter = 0;
                    MESSAGE = "Bad posture for " + 10 + " seconds";
                    dbA.AddAlert(TITLE, MESSAGE);
                }
            } else { timeCounter = 0; }
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}