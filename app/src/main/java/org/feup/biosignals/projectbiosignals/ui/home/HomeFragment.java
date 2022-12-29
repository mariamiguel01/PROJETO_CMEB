package org.feup.biosignals.projectbiosignals.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.feup.biosignals.projectbiosignals.MainActivity;
import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.FragmentHomeBinding;
import org.feup.biosignals.projectbiosignals.ui.alerts.Notifications;
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

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progress_bar);
        progressText = view.findViewById(R.id.progress_text);

        levelText = view.findViewById(R.id.level);
        pointsText = view.findViewById(R.id.points_text);
        imageToLoad = view.findViewById(R.id.image_level);

        Intent intent;
        intent = new Intent(getActivity(), video.class);
        intent.putExtra("points", points);
        startActivity(intent);

        if(getArguments() != null) {
            points = getArguments().getInt("points");
        }

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
                    handler.postDelayed(this, 200);
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
            }
        }, 200);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}