package org.feup.biosignals.projectbiosignals.ui.alerts;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.feup.biosignals.projectbiosignals.R;

import java.util.ArrayList;

public class AlertsListAdapter extends RecyclerView.Adapter<AlertsListAdapter.ViewHolder> {
    private ArrayList<classAlertItem> mAlerts;
    private OnItemListener mOnItemListener;
    Context context;

    public AlertsListAdapter(ArrayList<classAlertItem> mAlerts, OnItemListener onItemListener) {
        this.mAlerts = mAlerts;
        this.mOnItemListener = onItemListener;
    }

    public void addAlerts(classAlertItem alert) {
        if (!mAlerts.contains(alert)) {
            mAlerts.add(alert);
        }
    }

    public classAlertItem getAlert(int position) { return mAlerts.get(position); }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView alertTitle;
        public TextView alertText;

        OnItemListener onItemListener;

        public ViewHolder(View itemView, OnItemListener onItemListener) {
            super(itemView);
            alertTitle = (TextView) itemView.findViewById(R.id.alertRecTitle);
            alertText = (TextView) itemView.findViewById(R.id.alertRecText);

            this.onItemListener = onItemListener;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_alerts, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, mOnItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        classAlertItem alert = getAlert(position);

        final String alertTitle = alert.getTitle();
        if (alertTitle != null && alertTitle.length()>0){
            holder.alertTitle.setText(alertTitle);
            holder.alertText.setText(alert.getMessage());
        }
        else {
            holder.alertTitle.setText("No new notifications");
            holder.alertText.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return mAlerts.size();
    }
    public interface OnItemListener{
        void onItemClicked (int position);
    }


}
