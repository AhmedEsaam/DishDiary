package com.example.dishdiary.db;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface MealLocalDataSource {
    LiveData<List<Meal>> getStoredMeals();
    void insertMeal(Meal meal);
    void removeMeal(Meal meal);
    void checkMealExists(Meal meal);
}
