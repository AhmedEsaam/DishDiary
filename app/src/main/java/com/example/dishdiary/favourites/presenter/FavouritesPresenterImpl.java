package com.example.dishdiary.favourites.presenter;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.favourites.view.FavouritesView;
import com.example.dishdiary.meal_list.view.MealListView;
import com.example.dishdiary.model.Day;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepository;
import com.example.dishdiary.network.NetworkCallback;

import java.util.List;

public class FavouritesPresenterImpl implements FavouritesPresenter {
    private FavouritesView view;
    private MealsRepository repo;

    public FavouritesPresenterImpl(FavouritesView _view, MealsRepository _repo) {
        this.view = _view;
        this.repo = _repo;
    }


    @Override
    public LiveData<List<Meal>> getFavMeals() {
        return repo.getStoredMeals();
    }

    @Override
    public void removeFromFav(Meal meal) {
        meal.setFav(false);
        repo.deleteMeal(meal);
    }
}
