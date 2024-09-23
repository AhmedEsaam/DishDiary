package com.example.dishdiary.model;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.network.NetworkCallback;

import java.util.List;

public interface MealsRepository {
    public LiveData<List<Meal>> getStoredMeals();
    public void getAllMeals(NetworkCallback networkCallback);
    public void insertMeal(Meal meal);
    public void deleteMeal(Meal meal);
    public void checkMealExists(Meal meal);
}
