package org.feup.biosignals.projectbiosignals.ui.home;

import android.content.Intent;
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
import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.FragmentHomeBinding;
import org.feup.biosignals.projectbiosignals.ui.alerts.NotificationReceiver;

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
    int i = 20;
    int points;

    int timeCounter = 0;
    DBAlertsManager dbA;
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
                Log.i("angle raw", db_home.getPitchPB());
                back_angle = db_home.getPitchPB();
                progressText.setText(back_angle);
                progressBar.setProgress(Integer.parseInt(back_angle.replaceAll("[\\D]", "")));

                if (Math.round(Integer.parseInt(back_angle.replaceAll("[\\D]", ""))) > 15) { //back_angle > xx
                    timeCounter++;
                    if (timeCounter > i) {
                        Intent intent2notifications = new Intent(getContext(), NotificationReceiver.class);
                        MESSAGE = "Bad posture for " + i + " seconds"; //mudar para tempo correto
                        intent2notifications.putExtra("title", TITLE);
                        intent2notifications.putExtra("message", MESSAGE);
                        getActivity().sendBroadcast(intent2notifications);
                        timeCounter = 0;
                        dbA.AddAlert(TITLE, MESSAGE);
                    }
                } else { timeCounter = 0; }
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

            handler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}