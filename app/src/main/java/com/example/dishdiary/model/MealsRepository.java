package com.example.dishdiary.model;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.network.NetworkCallback;

import java.util.List;

public interface MealsRepository {
    // Remote Data Source
    public void getAllMeals(NetworkCallback networkCallback);

    // Local Data Source - Meals
    public LiveData<List<Meal>> getStoredMeals();
    public void insertMeal(Meal meal);
    public void deleteMeal(Meal meal);
    public void checkMealExists(Meal meal);

    // Local Data Source - Days
    public void insertDay(Day day);
    public void deleteDay(Day day);

    // Local Data Source - Day-Meal Entry
    public void insertDayMealEntry(DayMealJunction dayMealJunction);
    public LiveData<List<Meal>> getMealsOfDay(Day day);

    public Day getSun();
}
