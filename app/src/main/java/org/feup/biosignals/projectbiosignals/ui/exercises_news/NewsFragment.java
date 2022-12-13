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
import org.feup.biosignals.projectbiosignals.databinding.FragmentNewsBinding;
import org.feup.biosignals.projectbiosignals.show_news1;
import org.feup.biosignals.projectbiosignals.show_news2;

public class NewsFragment extends Fragment implements View.OnClickListener{

    private FragmentNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel settingsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button1 = (Button) root.findViewById(R.id.bt1);
        Button button2 = (Button) root.findViewById(R.id.bt2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.bt1:
                fragment = new show_news1();
                replaceFragment(fragment);
                break;

            case R.id.bt2:
                fragment = new show_news2();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}