package org.feup.biosignals.projectbiosignals.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.feup.biosignals.projectbiosignals.R;
import org.feup.biosignals.projectbiosignals.ui.alerts.classAlertItem;

import java.util.ArrayList;

public class AlertsListAdapter extends RecyclerView.Adapter<AlertsListAdapter.MyViewHolder> {
    private ArrayList<classAlertItem> mAlerts;
    //private OnItemListener mOnItemListener;
    Context context;

    public AlertsListAdapter(Context ct, ArrayList<classAlertItem> mAlerts) {
        this.mAlerts = mAlerts;
        this.context = ct;
    }

    public void addAlerts(classAlertItem alert) {
        if (!mAlerts.contains(alert)) {
            mAlerts.add(alert);
        }
    }

    public classAlertItem getAlert(int position) { return mAlerts.get(position); }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView alertTitle;
        public TextView alertText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            alertTitle = (TextView) itemView.findViewById(R.id.alertRecTitle);
            alertText = (TextView) itemView.findViewById(R.id.alertRecText);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_alert, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.alertTitle.setText(mAlerts.get(position).getTitle());
        holder.alertText.setText(mAlerts.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return mAlerts.size();
    }
    public interface OnItemListener{
        void onItemClicked (int position);
    }
}
