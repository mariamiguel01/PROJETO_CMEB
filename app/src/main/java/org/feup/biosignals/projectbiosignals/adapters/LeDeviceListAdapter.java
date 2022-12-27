package org.feup.biosignals.projectbiosignals.adapters;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
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

public class LeDeviceListAdapter extends RecyclerView.Adapter<LeDeviceListAdapter.ViewHolder> {
    private ArrayList<BluetoothDevice> mLeDevices;
    private OnItemListener mOnItemListener;

    public LeDeviceListAdapter(ArrayList<BluetoothDevice> mLeDevices, OnItemListener onItemListener) {
        this.mLeDevices = mLeDevices;
        this.mOnItemListener = onItemListener;
    }

    public void addDevice(BluetoothDevice device) {
        if (!mLeDevices.contains(device)) {
            mLeDevices.add(device);
        }
    }

    public BluetoothDevice getDevice(int position) {
        return mLeDevices.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemName;
        public TextView itemUUID;

        OnItemListener onItemListener;

        public ViewHolder(View itemView, OnItemListener onItemListener) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.textView);
            itemUUID = (TextView) itemView.findViewById(R.id.textView2);

            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClicked(getAdapterPosition());
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
        if (ActivityCompat.checkSelfPermission(holder.itemName.getContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
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
        return mLeDevices.size();
    }
    public interface OnItemListener{
        void onItemClicked (int position);
    }


}
