package com.example.dishdiary.favourites.view;

import com.example.dishdiary.model.Meal;

public interface OnFavouritesClickListener {
    void onLayoutClick(Meal meal);
    void onRemoveFromFavClick(Meal meal);

}
