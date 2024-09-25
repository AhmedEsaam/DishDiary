package com.example.dishdiary.db;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.model.Day;
import com.example.dishdiary.model.DayMealJunction;
import com.example.dishdiary.model.Meal;

import java.util.List;

public interface MealLocalDataSource {
    // Meals Table
    LiveData<List<Meal>> getStoredMeals();
    void insertMeal(Meal meal);
    void removeMeal(Meal meal);
    void checkMealExists(Meal meal);

    // Days Table
    void insertDay(Day day);
    void deleteDay(Day day);

    // Day-Meal Junction Table Entries
    void insertDayMealEntry(DayMealJunction dayMealJunction);
    void deleteDayMealEntry(DayMealJunction dayMealJunction);
    LiveData<List<Meal>> getMealsOfDay(Day day);

    //
    void deleteAllJunctions();
    void deleteAllDays();
    Day getSat();
    Day getSun();
    Day getMon();
    Day getTue();
    Day getWed();
    Day getThu();
    Day getFri();

}
