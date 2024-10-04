package com.example.dishdiary.model;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.datasources.db.MealLocalDataSource;

import com.example.dishdiary.datasources.network.NetworkCallback;
import com.example.dishdiary.datasources.network.MealRemoteDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealsRepositoryImpl implements MealsRepository {
    MealRemoteDataSource remoteDataSource;
    MealLocalDataSource localDataSource;
    private static MealsRepositoryImpl repo = null;

    public static MealsRepositoryImpl getInstance(MealRemoteDataSource remoteDataSource, MealLocalDataSource localDataSource) {
        if(repo == null) {
            repo = new MealsRepositoryImpl(remoteDataSource, localDataSource);
        }
//        localDataSource.deleteAllJunctions();
//        localDataSource.deleteAllDays();
        return repo;
    }

    private MealsRepositoryImpl(MealRemoteDataSource remoteDataSource, MealLocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public void getAllMeals(NetworkCallback networkCallback) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("s", "Ara");
        remoteDataSource.makeNetworkCall(networkCallback, "search.php", queryParams);
    }

    @Override
    public void filterMealsByName(NetworkCallback networkCallback, String name) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("s", name);
        remoteDataSource.makeNetworkCall(networkCallback, "search.php", queryParams);
    }

    @Override
    public void getRandomMeal(NetworkCallback networkCallback) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("s", "a");
        remoteDataSource.makeNetworkCall(networkCallback, "random.php", queryParams);
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() { return localDataSource.getStoredMeals(); }

    @Override
    public void insertMeal(Meal meal) { localDataSource.insertMeal(meal); }

    @Override
    public void deleteMeal(Meal meal) { localDataSource.removeMeal(meal); }

    @Override
    public void checkMealExists(Meal meal) { localDataSource.checkMealExists(meal); }

    @Override
    public LiveData<List<Meal>> getFavouriteMeals() { return localDataSource.getFavouriteMeals(); }

    @Override
    public void addFavMeal(Meal meal) {
        localDataSource.insertFavMeal(meal);
        localDataSource.insertMeal(meal);
    }

    @Override
    public void removeFavMeal(Meal meal) {
        localDataSource.removeFavMeal(meal);
    }

    @Override
    public void insertDayMealEntry(DayMealEntry dayMealEntry) {
        localDataSource.insertDayMealEntry(dayMealEntry);
    }

    @Override
    public LiveData<List<Meal>> getMealsOfDay(String day) {
        return localDataSource.getMealsOfDay(day);
    }

    @Override
    public LiveData<List<Meal>> getPlannedMeals() {
        return localDataSource.getAllPlannedMeals();
    }

}
