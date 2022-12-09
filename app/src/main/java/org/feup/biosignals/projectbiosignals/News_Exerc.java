package org.feup.biosignals.projectbiosignals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class News_Exerc extends AppCompatActivity {

    public Button button1;
    public Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);

        button1 = (Button) findViewById(R.id.bt1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(News_Exerc.this,show_news1.class);
                startActivity(intent);
            }
        });

        button2 = (Button) findViewById(R.id.bt2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(News_Exerc.this,show_news2.class);
                startActivity(intent);
            }
        });
    }
}
