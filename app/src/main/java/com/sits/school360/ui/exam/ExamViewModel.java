package com.sits.school360.ui.exam;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExamViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExamViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is exam fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}