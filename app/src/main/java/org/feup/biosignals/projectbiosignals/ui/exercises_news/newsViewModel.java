package org.feup.biosignals.projectbiosignals.ui.exercises_news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class newsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public newsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is news fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}