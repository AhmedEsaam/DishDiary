package com.example.dishdiary.features.meal_details.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.model.Day;
import com.example.dishdiary.model.Meal;

import java.util.List;

public interface MealDetailsPresenter {
    public void getIngredient();
    public void addToFav(Meal meal);
    public void removeFromFav(Meal meal);
}
