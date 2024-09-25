package com.example.dishdiary.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dishdiary.model.Day;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.DayMealJunction;

@Database(entities = {Meal.class, Day.class, DayMealJunction.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance = null;

    public abstract MealDAO getMealDAO();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "mealdb").build();
        }
        return instance;
    }
}
