package com.example.dishdiary.features.meal_list.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.model.Day;
import com.example.dishdiary.model.Meal;

import java.util.List;

public interface MealListPresenter {
    public void getAllMeals();
    public void addToFav(Meal meal);
    public void getRandomMeal();
    public void isMealExists(Meal meal);

    //test
    public void addMealToSunday(Meal meal);
    public LiveData<List<Meal>> getPlannedMeals(Day day);
}
