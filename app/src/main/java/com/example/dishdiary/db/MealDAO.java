package com.example.dishdiary.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdiary.model.Meal;

import java.util.List;

@Dao
public interface MealDAO {
    @Query("SELECT * FROM meals_table")
    LiveData<List<Meal>> getAllMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal meal);

    @Delete
    void deleteMeal(Meal meal);

    @Query("SELECT EXISTS(SELECT 1 FROM meals_table WHERE idMeal = :idMeal)")
    boolean isMealExists(String idMeal);
}
