package com.example.dishdiary.features.explore.view;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface ExploreView {

    public void showData(List<Meal> meals);
    public void showErrMsg(String error);
}
