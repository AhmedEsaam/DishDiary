package com.example.dishdiary.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dishdiary.model.Day;
import com.example.dishdiary.model.DayMealJunction;
import com.example.dishdiary.model.Meal;

import java.util.List;

public class MealLocalDataSourceImpl implements MealLocalDataSource {
    private static MealLocalDataSource MealLocalDataSource = null;

    private Context context;
    private AppDataBase db;
    private MealDAO mealDAO;
    private LiveData<List<Meal>> storedMeals;
//    private LiveData<List<Meal>> mealsOfDay;

    private static Day sat, sun, mon, tue, wed, thu, fri;

    private MealLocalDataSourceImpl(Context context) {
        this.context = context;
        db = AppDataBase.getInstance(context.getApplicationContext());
        mealDAO = db.getMealDAO();
        storedMeals = mealDAO.getAllMeals();

        sat = new Day("Saturday");
        sun = new Day("Sunday");
        mon = new Day("Monday");
        tue = new Day("Tuesday");
        wed = new Day("Wednesday");
        thu = new Day("Thursday");
        fri = new Day("Friday");

    }

    public static MealLocalDataSource getInstance(Context context) {
        if (MealLocalDataSource == null) {
            MealLocalDataSource = new MealLocalDataSourceImpl(context);

            // Add days of the week
//            MealLocalDataSource.insertDay(sat);
//            MealLocalDataSource.insertDay(sun);
//            MealLocalDataSource.insertDay(mon);
//            MealLocalDataSource.insertDay(tue);
//            MealLocalDataSource.insertDay(wed);
//            MealLocalDataSource.insertDay(thu);
//            MealLocalDataSource.insertDay(fri);
        }
        return MealLocalDataSource;
    }

    public Day getSat() {
        return sat;
    }

    public Day getSun() {
        return sun;
    }

    public Day getMon() {
        return mon;
    }

    public Day getTue() {
        return tue;
    }

    public Day getWed() {
        return wed;
    }

    public Day getThu() {
        return thu;
    }

    public Day getFri() {
        return fri;
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return storedMeals;
    }

    @Override
    public void insertMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertMeal(meal);
            }
        }).start();
    }

    @Override
    public void removeMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteMeal(meal);
            }
        }).start();
    }

    @Override
    public void checkMealExists(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                meal.setFav(mealDAO.isMealExists(meal.getIdMeal()));
            }
        }).start();
    }

    // Days Plan

    @Override
    public void insertDay(Day day) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertDay(day);
            }
        }).start();
    }

    @Override
    public void deleteDay(Day day) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteDay(day);
            }
        }).start();
    }

    @Override
    public void insertDayMealEntry(DayMealJunction dayMealJunction) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertDayMealEntry(dayMealJunction);
            }
        }).start();
    }

    @Override
    public void deleteDayMealEntry(DayMealJunction dayMealJunction) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteDayMealEntry(dayMealJunction);
            }
        }).start();
    }

    @Override
    public LiveData<List<Meal>> getMealsOfDay(Day day) {
        return mealDAO.getMealsOfDay(day.getDayName());
    }


    ////
    @Override
    public void deleteAllJunctions() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteAllJunctions();
            }
        }).start();

    }

    @Override
    public void deleteAllDays() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteAllDays();
            }
        }).start();
    }


}
