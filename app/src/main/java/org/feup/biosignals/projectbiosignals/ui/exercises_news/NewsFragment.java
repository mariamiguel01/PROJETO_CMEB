package org.feup.biosignals.projectbiosignals.ui.exercises_news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import org.feup.biosignals.projectbiosignals.News_Exerc;
import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.video;
import org.feup.biosignals.projectbiosignals.databinding.FragmentNewsBinding;
import org.feup.biosignals.projectbiosignals.show_news1;
import org.feup.biosignals.projectbiosignals.show_news2;

public class NewsFragment extends Fragment{

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel settingsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_news, container, false);

        Button button1 = (Button) root.findViewById(R.id.bt1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment_activity_main, new show_news1());
                fr.commit();
            }
        });

        Button button2 = (Button) root.findViewById(R.id.bt2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = getParentFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment_activity_main, new show_news2());
                fr.commit();
            }
        });

        Intent intent1;
        intent1 = new Intent(getActivity(), video.class);
        final Button button3 = (Button) root.findViewById(R.id.bt3);

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent1);
            }
        });

        return root;
    }

}