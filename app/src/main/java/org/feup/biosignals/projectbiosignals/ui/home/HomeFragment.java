package org.feup.biosignals.projectbiosignals.ui.home;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
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
    int i = 0;
    int points;

    int timeCounter = 0;

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
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String result = bundle.getString("bundleKey");
                // Do something with the result
                Log.i("Comunication", result);
            }
        });
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i("Com_inicial", Integer.toString(points));

        progressBar = view.findViewById(R.id.progress_bar);
        progressText = view.findViewById(R.id.progress_text);

        levelText = view.findViewById(R.id.level);
        pointsText = view.findViewById(R.id.points_text);
        imageToLoad = view.findViewById(R.id.image_level);

        getParentFragmentManager().setFragmentResultListener("changedPoints", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String changedPoints, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                points = bundle.getInt("video_points");
                // Do something with the result
                Log.i("Com_receive", Integer.toString(points));
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (i <= 100) {
                    progressText.setText("" + i);
                    progressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this, 1000);
                } else {
                    handler.removeCallbacks(this);
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
                Log.i("Com_send", Integer.toString(points));

                if (i > 5) {
                    timeCounter++;
                    if (timeCounter > 5) {
                        Intent intent2notifications = new Intent(getContext(), NotificationReceiver.class);
                        //intent2notifications.setAction("");
                        getActivity().sendBroadcast(intent2notifications);
                        timeCounter = 0;
                    }
                } else { timeCounter = 0; }
            }
        }, 1000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}