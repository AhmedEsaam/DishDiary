package com.example.dishdiary.meal_list.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.home.view.HomeView;
import com.example.dishdiary.meal_list.view.MealListView;
import com.example.dishdiary.model.Day;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepository;
import com.example.dishdiary.network.NetworkCallback;

import java.util.List;

public class MealListPresenterImpl implements MealListPresenter, NetworkCallback {
    private MealListView _view;
    private MealsRepository _repo;

    public MealListPresenterImpl(MealListView _view, MealsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }


    @Override
    public void getAllMeals() { _repo.getAllMeals(this); }
//    public void getAllMeals() { _view.showData(_repo.getMealsOfDay(sun).getValue()); }

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

    // test
    public void addMealToSunday(Meal meal) {
//
//        _repo.insertDay(sun);

//        DayMealJunction dayMealJunction = new DayMealJunction(day.getIdDay(), meal.getIdMeal());
    }

    public LiveData<List<Meal>> getPlannedMeals(Day day) {
        return _repo.getMealsOfDay(day);
//        return null;
    }
}
