package com.example.dishdiary.meal_details.view;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface MealDetailsView {

    public void showData(List<Meal> meals);
    public void showErrMsg(String error);
}
