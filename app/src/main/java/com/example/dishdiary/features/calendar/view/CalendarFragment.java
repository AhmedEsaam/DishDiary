package com.example.dishdiary.features.calendar.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdiary.databinding.FragmentCalendarBinding;
import com.example.dishdiary.databinding.FragmentMeallistBinding;
import com.example.dishdiary.datasources.db.MealLocalDataSourceImpl;
import com.example.dishdiary.datasources.network.MealRemoteDataSourceImpl;
import com.example.dishdiary.features.calendar.presenter.CalendarPresenter;
import com.example.dishdiary.features.calendar.presenter.CalendarPresenterImpl;
import com.example.dishdiary.features.meal_details.view.MealDetailsActivity;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepositoryImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment implements OnCalendarClickListener, CalendarView {

    private FragmentCalendarBinding binding;

    private static final String TAG = "CalendarFragment";

    // Incoming Data
    String filterBy, value;

    // Presenter
    CalendarPresenter calendarPresenter;

    // UI
    private RecyclerView recyclerView;
    private CalendarAdapter calendarAdapter;
    private LinearLayoutManager layoutManager;

    LiveData<List<Meal>> plannedMeals;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        ((MainActivity) requireActivity()).hideBottomNavBar();
//
//        // Sharing data
//        CalendarViewModel calendarViewModel = new ViewModelProvider(requireActivity()).get(CalendarViewModel.class);
//        calendarViewModel.getMealsList().observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
//            @Override
//            public void onChanged(List<Meal> meals) {
//                if(meals != null)
//                    showData(meals);
//                else
//                    Toast.makeText(getContext(), "No meals found", Toast.LENGTH_SHORT).show();
//            }
//        });

        ///
//        DashboardViewModel dashboardViewModel = new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);


//        final Button btn = binding.btn;
//        btn.setOnClickListener(view -> dashboardViewModel.setText(txtDash.getText().toString()));

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();

        // Presenter
        calendarPresenter = new CalendarPresenterImpl(
                this,
                MealsRepositoryImpl.getInstance(
                        MealRemoteDataSourceImpl.getInstance(),
                        MealLocalDataSourceImpl.getInstance(getActivity())
                )
        );


        // UI
        recyclerView.setHasFixedSize(true);
        calendarAdapter = new CalendarAdapter(this.getContext(), new ArrayList<Meal>(), this);
        layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(calendarAdapter);

        // Observe on data
        plannedMeals = calendarPresenter.getPlannedMeals();

        plannedMeals.observe(getViewLifecycleOwner(), this::showData);
    }

    private void initUI() {
        recyclerView = (RecyclerView) binding.recycViewMealList;
    }


    // implementation of HomeView methods

    @Override
    public void showData(List<Meal> meals) {
        calendarAdapter.setList(meals);
        calendarAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Implementation f OnHomeClickListener methods

    @Override
    public void onLayoutClick(Meal meal) {
//        Toast.makeText(this.getContext(), meal.toString(), Toast.LENGTH_SHORT).show();
        Intent outIntent = new Intent(getActivity(), MealDetailsActivity.class);
        outIntent.putExtra("mealDetails", meal);
        startActivity(outIntent);
    }

    @Override
    public void onAddToFavClick(Meal meal) {
        calendarPresenter.addToFav(meal);
        calendarAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAddToCalendar(Meal meal) {
        Calendar calendar;
        calendar = Calendar.getInstance();

        // Get the current date
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        final String[] date = new String[1];

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                (view, year1, month1, dayOfMonth) -> {
                    date[0] = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                    calendarPresenter.addToFav(meal);
                    calendarPresenter.insertDayMealEntry(date[0], meal.getIdMeal());
                    Log.i("Date...", dayOfMonth + "/" + (month1 + 1) + "/" + year1);
                    },
                year, month, day);

        // Show the DatePickerDialog
        datePickerDialog.show();

//        mealListPresenter.insertDayMealEntry(date[0], meal.getIdMeal());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ((MainActivity) requireActivity()).showBottomNavBar();
    }
}