package com.example.dosemonitor.ui.adddose;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddDoseViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AddDoseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}