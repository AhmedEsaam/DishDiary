package com.example.dishdiary.network;

import com.example.dishdiary.model.MealsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {
    @GET("search.php?s=Ara")
    Call<MealsResponse> getMealResponse();
//
//    @GET("search.php?s=Ara")
//    Call<MealsResponse> getMealResponse();
}