package com.example.dishdiary.home.view;

import com.example.dishdiary.model.Meal;

public interface OnHomeClickListener {
    void onLayoutClick(Meal meal);
    void onAddToFavClick(Meal meal);
}
