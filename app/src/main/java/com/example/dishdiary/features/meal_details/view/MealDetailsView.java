package com.example.dishdiary.features.meal_details.view;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface MealDetailsView {

    public void showData(Meal meal);
    public void showErrMsg(String error);
}
