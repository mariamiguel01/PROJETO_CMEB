package org.feup.biosignals.projectbiosignals.ui.alerts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.feup.biosignals.projectbiosignals.databinding.FragmentAlertsBinding;

public class AlertsFragment extends Fragment {

    private FragmentAlertsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AlertsViewModel alertsViewModel =
                new ViewModelProvider(this).get(AlertsViewModel.class);

        binding = FragmentAlertsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textalerts;
        alertsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}