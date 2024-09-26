package com.example.dishdiary.meal_list.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MealListViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MealListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is meal list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String data) {
        mText.setValue(data);
    }
}