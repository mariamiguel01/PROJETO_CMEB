package org.feup.biosignals.projectbiosignals.ui.alerts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AlertsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AlertsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Recent alerts:");
    }

    public LiveData<String> getText() {
        return mText;
    }
}