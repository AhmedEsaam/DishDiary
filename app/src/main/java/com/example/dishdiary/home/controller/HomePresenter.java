package com.example.dishdiary.home.controller;

import com.example.dishdiary.model.Meal;

public interface HomePresenter {
    public void getAllMeals();
    public void addToFav(Meal meal);
    public void getRandomMeal();
}
