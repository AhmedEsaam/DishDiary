package com.example.dishdiary.features.explore.presenter;

public interface ExplorePresenter {
    public void getAllMeals();
    public void getAllCategories();
    public void getAllAreas();
    public void filterByName(String name);
    public void filterByCategory(String category);
    public void filterByArea(String area);
}
