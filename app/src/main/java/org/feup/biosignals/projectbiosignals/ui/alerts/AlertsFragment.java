package org.feup.biosignals.projectbiosignals.ui.alerts;

import android.os.Handler;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.feup.biosignals.projectbiosignals.DatabaseALertsHelper;
import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.adapters.AlertsListAdapter;

import java.util.ArrayList;


public class AlertsFragment extends Fragment implements AlertsListAdapter.OnItemListener {
    //private FragmentAlertsBinding binding;
    private View root;

    private ArrayList<classAlertItem> mAlerts = new ArrayList<classAlertItem>();
    private classAlertItem mAlert;
    //private AlertsListAdapter.OnItemListener mOnItemListener;
    private static final long SCAN_PERIOD = 10000;

    private AlertsListAdapter alertsListAdapter;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerViewAlerts;
    //private Handler mHandler;
    DatabaseALertsHelper dbAlerts;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AlertsViewModel alertsViewModel =
                new ViewModelProvider(this).get(AlertsViewModel.class);

        root = inflater.inflate(R.layout.fragment_alerts_list, container, false);
        /*recyclerview = view.findViewById(R.id.recyclerViewAlerts);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        AlertsListAdapter alertsListAdapter = new AlertsListAdapter(mAlerts, mOnItemListener);
        recyclerViewAlerts.setAdapter(alertsListAdapter);*/
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewAlerts = view.findViewById(R.id.recyclerViewAlerts);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerViewAlerts.setLayoutManager(layoutManager);

        alertsListAdapter = new AlertsListAdapter(getContext(), mAlerts);
        recyclerViewAlerts.setAdapter(alertsListAdapter);

        dbAlerts = new DatabaseALertsHelper(getContext());
        dbAlerts.getAlertsByDate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        root = null;
    }

    @Override
    public void onItemClicked(int position) {

    }
}