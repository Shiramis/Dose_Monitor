package com.example.dosemonitor.ui.yeartotal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YearTotalViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public YearTotalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}