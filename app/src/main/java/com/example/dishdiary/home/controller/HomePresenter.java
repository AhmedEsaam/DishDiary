package com.example.dishdiary.home.controller;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.model.Day;
import com.example.dishdiary.model.Meal;

import java.util.List;

public interface HomePresenter {
    public void getAllMeals();
    public void addToFav(Meal meal);
    public void getRandomMeal();

    //test
    public void addMealToSunday(Meal meal);
    public LiveData<List<Meal>> getPlannedMeals(Day day);
}
