package com.sits.school360.ui.homework;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeworkViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeworkViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is homework fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}