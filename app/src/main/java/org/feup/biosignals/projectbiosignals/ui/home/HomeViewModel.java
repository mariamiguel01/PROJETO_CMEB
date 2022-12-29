package org.feup.biosignals.projectbiosignals.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Let's see how your posture is doing:");
    }

    public LiveData<String> getText() {
        return mText;
    }
}