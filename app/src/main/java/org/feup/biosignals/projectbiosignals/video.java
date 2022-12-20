package org.feup.biosignals.projectbiosignals;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class video extends AppCompatActivity {

    String api_key = "AIzaSyBsVUeqyL52AhSJB9Z126oxe12RCHISTnI";

    @Override
    protected void
    onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        // Get reference to the view of Video player
        YouTubePlayerView video = findViewById(R.id.ytPlayer);

        video.initialize(
                api_key,
                new YouTubePlayer.OnInitializedListener() {
                    public void onInitializationSuccess(
                            YouTubePlayer.Provider provider,
                            YouTubePlayer youTubePlayer, boolean b)
                    {
                        youTubePlayer.loadVideo("https://youtu.be/5R54QoUbbow");
                        youTubePlayer.play();
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult
                                                                youTubeInitializationResult)
                    {
                        Toast.makeText(getApplicationContext(), "Video player Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}