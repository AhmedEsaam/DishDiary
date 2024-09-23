package com.example.dishdiary.home.controller;

import com.example.dishdiary.home.view.HomeView;
import com.example.dishdiary.model.MealsRepository;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.network.NetworkCallback;

import java.util.List;

public class HomePresenterImpl implements HomePresenter, NetworkCallback {
    private HomeView _view;
    private MealsRepository _repo;

    public HomePresenterImpl(HomeView _view, MealsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getAllMeals() { _repo.getAllMeals(this); }

    @Override
    public void addToFav(Meal Meal) {
        Meal.setFav(true);
        _repo.insertMeal(Meal);
    }

    @Override
    public void onSuccessResult(List<Meal> Meals) {
        for(Meal Meal : Meals) {
            _repo.checkMealExists(Meal);
        }
        _view.showData(Meals);
    }

    @Override
    public void onFailureResult(String errorMsg) { _view.showErrMsg(errorMsg); }

    @Override
    public void getRandomMeal() {

    }
}
