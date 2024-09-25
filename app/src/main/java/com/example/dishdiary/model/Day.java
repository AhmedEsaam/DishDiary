package com.example.dishdiary.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "days_table")
public class Day {
    @PrimaryKey(autoGenerate = true)
    public int idDay;

    public String dayName;

    public Day(String dayName) {
        this.dayName = dayName;
    }

    public int getIdDay() {
        return idDay;
    }

    public String getDayName() {
        return dayName;
    }

}
