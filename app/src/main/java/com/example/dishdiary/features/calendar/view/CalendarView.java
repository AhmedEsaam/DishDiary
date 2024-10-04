package com.example.dishdiary.features.calendar.view;

import com.example.dishdiary.model.Meal;

import java.util.List;

public interface CalendarView {

    public void showData(List<Meal> meals);
    public void showErrMsg(String error);
}
