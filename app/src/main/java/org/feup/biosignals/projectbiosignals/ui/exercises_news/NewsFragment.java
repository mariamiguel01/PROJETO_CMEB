package org.feup.biosignals.projectbiosignals.ui.exercises_news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.video;
import org.feup.biosignals.projectbiosignals.news1;
import org.feup.biosignals.projectbiosignals.news2;

public class NewsFragment extends Fragment{

    int points;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel settingsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_news, container, false);

        getParentFragmentManager().setFragmentResultListener("requestPoints", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestPoints, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                points = bundle.getInt("points");
                // Do something with the result
                //Log.i("Comunication", Integer.toString(points));
            }
        });

        Intent intent3;
        intent3 = new Intent(getActivity(), news1.class);
        Button button1 = (Button) root.findViewById(R.id.bt1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points ++;
                Bundle result = new Bundle(); // --> Enviar a informação
                result.putInt("points", points);
                getParentFragmentManager().setFragmentResult("changedPoints", result);
                startActivity(intent3);
            }
        });

        Intent intent2;
        intent2 = new Intent(getActivity(), news2.class);
        Button button2 = (Button) root.findViewById(R.id.bt2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points ++;
                Bundle result = new Bundle(); // --> Enviar a informação
                result.putInt("points", points);
                getParentFragmentManager().setFragmentResult("changedPoints", result);
                startActivity(intent2);

            }
        });

        Intent intent1;
        intent1 = new Intent(getActivity(), video.class);
        final Button button3 = (Button) root.findViewById(R.id.bt3);

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                points ++;
                Bundle result = new Bundle(); // --> Enviar a informação
                result.putInt("points", points);
                getParentFragmentManager().setFragmentResult("changedPoints", result);
                Log.i("Comunication", Integer.toString(points));
                startActivity(intent1);
            }
        });

        return root;
    }

}