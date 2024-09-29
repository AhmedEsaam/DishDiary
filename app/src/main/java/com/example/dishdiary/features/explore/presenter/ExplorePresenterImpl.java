package com.example.dishdiary.features.explore.presenter;

import com.example.dishdiary.features.home.view.HomeView;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepository;
import com.example.dishdiary.datasources.network.NetworkCallback;

import java.util.List;

public class ExplorePresenterImpl implements ExplorePresenter, NetworkCallback {
    private HomeView view;
    private MealsRepository repo;

    public ExplorePresenterImpl(HomeView _view, MealsRepository _repo) {
        this.view = _view;
        this.repo = _repo;
    }


    @Override
    public void getAllMeals() { repo.getAllMeals(this); }


    @Override
    public void onSuccessResult(List<Meal> meals) {
        for(Meal Meal : meals) {
            repo.checkMealExists(Meal);
        }
        view.showData(meals);
    }

    @Override
    public void onFailureResult(String errorMsg) { view.showErrMsg(errorMsg); }

}
