package com.example.dishdiary.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "day_meal_table", primaryKeys = {"idDay", "idMeal"})
public class DayMealJunction {
    public int idDay;
    @NonNull
    public String idMeal;

    public DayMealJunction(int idDay, String idMeal) {
        this.idDay = idDay;
        this.idMeal = idMeal;
    }
}