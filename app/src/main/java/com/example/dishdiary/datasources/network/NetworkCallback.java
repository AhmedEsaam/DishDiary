package com.example.dishdiary.datasources.network;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface NetworkCallback {
    void onSuccessResult(List<Meal> meals);
    void onFailureResult(String errorMsg);
}
