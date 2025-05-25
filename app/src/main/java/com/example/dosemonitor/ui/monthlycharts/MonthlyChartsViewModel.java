package com.example.dosemonitor.ui.monthlycharts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MonthlyChartsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MonthlyChartsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}