package org.feup.biosignals.projectbiosignals;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WebVideoFragment extends Fragment{
    private VideoView videoView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_video, container, false);
        videoView = view.findViewById(R.id.fragment_webvideo_videoView);
        Button playButton;
        playButton = view.findViewById(R.id.fragment_webvideo_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playVideoFromWeb();
            }
        });

        return view;
    }
    private void playVideoFromWeb() {
        Uri uri= Uri.parse("https://youtu.be/5R54QoUbbow");
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this.getActivity());
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}