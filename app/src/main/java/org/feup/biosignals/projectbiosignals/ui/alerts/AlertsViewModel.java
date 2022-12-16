package org.feup.biosignals.projectbiosignals.ui.alerts;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.feup.biosignals.projectbiosignals.R;

public class AlertsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AlertsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is alerts fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}