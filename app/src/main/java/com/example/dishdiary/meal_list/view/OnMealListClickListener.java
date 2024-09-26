package com.example.dishdiary.meal_list.view;

import com.example.dishdiary.model.Meal;

public interface OnMealListClickListener {
    void onLayoutClick(Meal meal);
    void onAddToFavClick(Meal meal);
}
