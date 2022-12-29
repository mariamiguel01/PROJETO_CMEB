package org.feup.biosignals.projectbiosignals.ui.alerts;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.feup.biosignals.projectbiosignals.R;

import java.util.ArrayList;

public class ShowAlerts extends Fragment implements AlertsListAdapter.OnItemListener {

    private ArrayList<classAlertItem> mAlerts = new ArrayList<classAlertItem>();
    private classAlertItem mAlert;
    private AlertsListAdapter.OnItemListener mOnItemListener;
    private static final long SCAN_PERIOD = 10000;

    private AlertsListAdapter alertsListAdapter;
    private RecyclerView recyclerview;

    RecyclerView.LayoutManager layoutManager;

    RecyclerView recyclerViewAlerts;
    String titles[], messages[];

    private Handler mHandler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AlertsViewModel alertsViewModel =
                new ViewModelProvider(this).get(AlertsViewModel.class);

        /*recyclerview = view.findViewById(R.id.recyclerViewAlerts);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        AlertsListAdapter alertsListAdapter = new AlertsListAdapter(mAlerts, mOnItemListener);
        recyclerViewAlerts.setAdapter(alertsListAdapter);*/

        return inflater.inflate(R.layout.fragment_alerts_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewAlerts = recyclerViewAlerts.findViewById(R.id.recyclerViewAlerts);

       // titles = getResources().getStringArray();
        //mAlerts.add(new classAlertItem("Title test", "Message test"));

        //mHandler = new Handler();

    }

    @Override
    public void onItemClicked(int position) {

    }
}
