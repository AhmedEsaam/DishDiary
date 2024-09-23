package com.example.dishdiary.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.model.Meal;

import java.util.List;

public class MealLocalDataSourceImpl implements MealLocalDataSource {
    private static MealLocalDataSource MealLocalDataSource = null;

    private Context context;
    private AppDataBase db;
    private MealDAO mealDAO;
    private LiveData<List<Meal>> storedMeals;

    private MealLocalDataSourceImpl(Context context) {
        this.context = context;
        db = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.getMealDAO();
        storedMeals = mealDAO.getAllMeals();
    }

    public static MealLocalDataSource getInstance(Context context) {
        if (MealLocalDataSource == null) {
            MealLocalDataSource = new MealLocalDataSourceImpl(context);
        }
        return MealLocalDataSource;
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return storedMeals;
    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() { mealDAO.insertMeal(meal); }
        }).start();
    }

    @Override
    public void removeMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() { mealDAO.deleteMeal(meal); }
        }).start();
    }

    @Override
    public void checkMealExists(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() { meal.setFav(mealDAO.isMealExists(meal.getIdMeal())); }
        }).start();
    }
}
