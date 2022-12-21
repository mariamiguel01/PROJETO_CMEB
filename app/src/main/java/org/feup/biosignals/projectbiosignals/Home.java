package org.feup.biosignals.projectbiosignals;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Home extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    private TextView pointsText;
    int i = 0;
    int points; //Como definir os pontos?
    int imageToLoad =  0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        // set the id for the progressbar and progress text
        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);

        pointsText = findViewById(R.id.level);

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
                    imageToLoad = 1;
                    pointsText.setText("level1");
                }
                if (points>=5 && points<10) {
                    imageToLoad = 2;
                    pointsText.setText("level2");
                }
                if (points>=10 && points<15) {
                    imageToLoad = 3;
                    pointsText.setText("level3");
                }
                if (points>=15 && points<20) {
                    imageToLoad = 4;
                    pointsText.setText("level4");
                }
                if (points>=20 && points<25) {
                    imageToLoad = 5;
                    pointsText.setText("level5");
                }
                else {
                    imageToLoad = 6;
                    pointsText.setText("level6");
                }
            }
        }, 200);
    }
}