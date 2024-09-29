package com.example.dishdiary.features.meal_details.presenter;

import com.example.dishdiary.features.meal_details.view.MealDetailsView;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepository;
import com.example.dishdiary.datasources.network.NetworkCallback;

import java.util.List;

public class MealDetailsPresenterImpl implements MealDetailsPresenter, NetworkCallback {
    private MealDetailsView _view;
    private MealsRepository _repo;

    public MealDetailsPresenterImpl(MealDetailsView _view, MealsRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getIngredient() {

    }

    @Override
    public void addToFav(Meal meal) {
        meal.setFav(true);
        _repo.insertMeal(meal);
    }

    @Override
    public void removeFromFav(Meal meal) {
        meal.setFav(false);
        _repo.deleteMeal(meal);
    }

    @Override
    public void onSuccessResult(List<Meal> meals) {
//        for(Meal Meal : meals) {
//            _repo.checkMealExists(Meal);
//        }
//        _view.showData(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) { _view.showErrMsg(errorMsg); }

}
