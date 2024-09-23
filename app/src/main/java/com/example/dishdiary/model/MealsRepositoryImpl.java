package com.example.dishdiary.model;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.db.MealLocalDataSource;

import com.example.dishdiary.network.NetworkCallback;
import com.example.dishdiary.network.MealRemoteDataSource;

import java.util.List;

public class MealsRepositoryImpl implements MealsRepository {
    MealRemoteDataSource remoteDataSource;
    MealLocalDataSource localDataSource;
    private static MealsRepositoryImpl repo = null;

    public static MealsRepositoryImpl getInstance(MealRemoteDataSource remoteDataSource, MealLocalDataSource localDataSource) {
        if(repo == null) {
            repo = new MealsRepositoryImpl(remoteDataSource, localDataSource);
        }
        return repo;
    }

    private MealsRepositoryImpl(MealRemoteDataSource remoteDataSource, MealLocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() { return localDataSource.getStoredMeals(); }

    @Override
    public void getAllMeals(NetworkCallback networkCallback) {
        remoteDataSource.makeNetworkCall(networkCallback);
    }

    @Override
    public void insertMeal(Meal meal) { localDataSource.insertMeal(meal); }

    @Override
    public void deleteMeal(Meal meal) { localDataSource.removeMeal(meal); }

    @Override
    public void checkMealExists(Meal meal) { localDataSource.checkMealExists(meal); }
}
