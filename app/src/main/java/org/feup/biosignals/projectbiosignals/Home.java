package org.feup.biosignals.projectbiosignals;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;


public class Home extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    private TextView pointsText;
    private ImageView imageToLoad;
    int i = 0;
    Intent mIntent = getIntent();
    int points = mIntent.getIntExtra("points", 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        // set the id for the progressbar and progress text
        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);

        pointsText = findViewById(R.id.level);
        imageToLoad = (ImageView)findViewById(R.id.image_level);

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
}