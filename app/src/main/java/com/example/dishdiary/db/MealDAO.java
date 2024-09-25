package com.example.dishdiary.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdiary.model.Day;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.DayMealJunction;

import java.util.List;

@Dao
public interface MealDAO {
    // Meal table
    @Query("SELECT * FROM meals_table")
    LiveData<List<Meal>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

    @Query("SELECT EXISTS(SELECT 1 FROM meals_table WHERE idMeal = :idMeal)")
    boolean isMealExists(String idMeal);

    // Day table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDay(Day day);

    @Delete
    void deleteDay(Day day);

    // Meal-Day Junction table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDayMealEntry(DayMealJunction dayMealJunction);

    @Delete
    void deleteDayMealEntry(DayMealJunction dayMealJunction);

    @Query("SELECT meals_table.* FROM meals_table " +
            "JOIN day_meal_table ON meals_table.idMeal = day_meal_table.idMeal " +
            "JOIN days_table ON day_meal_table.idDay = days_table.idDay " +
            "WHERE days_table.dayName = :dayName")
    LiveData<List<Meal>> getMealsOfDay(String dayName);

    @Query("DELETE FROM day_meal_table")
    void deleteAllJunctions();

    @Query("DELETE FROM days_table")
    void deleteAllDays();

}
