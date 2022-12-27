/*
package org.feup.biosignals.projectbiosignals.ui.alerts;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.feup.biosignals.projectbiosignals.R;

import java.util.ArrayList;

public class AlertsListAdapter extends RecyclerView.Adapter<AlertsListAdapter.ViewHolder> {
    private ArrayList<Alerts> mAlerts;
    private OnItemListener mOnItemListener;

    public AlertsListAdapter(ArrayList<Alerts> mAlerts, OnItemListener onItemListener) {
        this.mAlerts = mAlerts;
        this.mOnItemListener = onItemListener;
    }

    public void addAlerts(Alerts alert) {
        if (!mAlerts.contains(alert)) {
            mAlerts.add(alert);
        }
    }

    public NewAlerts getDevice(int position) {
        return mLeDevices.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName;
        public TextView itemUUID;

        OnItemListener onItemListener;

        public ViewHolder(View itemView, OnItemListener onItemListener) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.textView);
            itemUUID = (TextView) itemView.findViewById(R.id.textView2);

            this.onItemListener = onItemListener;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_view_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v, mOnItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BluetoothDevice device = getDevice(position);

        final String deviceName = device.getName();
        if (deviceName != null && deviceName.length()>0){
            holder.itemName.setText(deviceName);
        }
        else {
            holder.itemName.setText("Unknown Device");
        }
        holder.itemUUID.setText(device.getAddress());
    }

    @Override
    public int getItemCount() {
        return mAlerts.size();
    }
    public interface OnItemListener{
        void onItemClicked (int position);
    }


}
*/
