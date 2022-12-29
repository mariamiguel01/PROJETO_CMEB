package org.feup.biosignals.projectbiosignals;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.feup.biosignals.projectbiosignals.ui.home.HomeFragment;
import org.feup.biosignals.projectbiosignals.ui.home.HomeViewModel;

public class video extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView1;
    int points = 0;

    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);
        youTubePlayerView1 = findViewById(R.id.ytPlayer1);

        YouTubePlayer.OnInitializedListener listener= new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("5R54QoUbbow");
                youTubePlayer.play();
                Intent intent = getIntent();
                points = intent.getIntExtra("points_main", points);
                Log.i("points ", Integer.toString(points));
                points ++;
                Log.i("points ", Integer.toString(points));
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();
            }

        };
        youTubePlayerView1.initialize("AIzaSyBUdsYQM6Xe5EqWk8_J9R3UNu6xNuSbWcE",listener);


        passfragment();

    }

    private void passfragment() {
        Bundle bundle = new Bundle();
        bundle.putInt("points", points);
        Fragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
    }

}