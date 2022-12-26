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

import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.databinding.FragmentHomeBinding;
import org.feup.biosignals.projectbiosignals.ui.alerts.Notifications;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ProgressBar progressBar;
    private TextView progressText;
    private TextView pointsText;
    private ImageView imageToLoad;
    int i = 0;

    int points = 8;


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

        pointsText = view.findViewById(R.id.level);
        imageToLoad = view.findViewById(R.id.image_level);

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

                if (points<5) {
                    imageToLoad.setImageResource(R.drawable.level1);
                    pointsText.setText("level1");
                }
                if (points>=5 && points<10) {
                    imageToLoad.setImageResource(R.drawable.level2);
                    pointsText.setText("level2");
                }
                if (points>=10 && points<15) {
                    imageToLoad.setImageResource(R.drawable.level3);
                    pointsText.setText("level3");
                }
                if (points>=15 && points<20) {
                    imageToLoad.setImageResource(R.drawable.level4);
                    pointsText.setText("level4");
                }
                if (points>=20 && points<25) {
                    imageToLoad.setImageResource(R.drawable.level5);
                    pointsText.setText("level5");
                }
                else {
                    imageToLoad.setImageResource(R.drawable.level6);
                    pointsText.setText("level6");
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