package org.feup.biosignals.projectbiosignals;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class video extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView1;
    int points;

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
                points ++;
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();
            }

        };
        youTubePlayerView1.initialize("AIzaSyBUdsYQM6Xe5EqWk8_J9R3UNu6xNuSbWcE",listener);

        Intent pointsIntent = new Intent(video.this, Home.class);
        pointsIntent.putExtra("points", points);
        startActivity(pointsIntent);
    }
}