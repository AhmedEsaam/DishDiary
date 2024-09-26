package com.example.dishdiary.meal_list.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdiary.databinding.FragmentHomeBinding;
import com.example.dishdiary.databinding.FragmentMeallistBinding;
import com.example.dishdiary.db.MealLocalDataSourceImpl;
import com.example.dishdiary.home.presenter.HomePresenter;
import com.example.dishdiary.home.presenter.HomePresenterImpl;
import com.example.dishdiary.meal_list.presenter.MealListPresenter;
import com.example.dishdiary.meal_list.presenter.MealListPresenterImpl;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepositoryImpl;
import com.example.dishdiary.network.MealRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.List;

public class MealListFragment extends Fragment implements OnMealListClickListener, MealListView {

    private static final String TAG = "MealListFragment";

    // Presenter
    MealListPresenter mealListPresenter;

    // UI
    private RecyclerView recyclerView;
    private MealListAdapter mealListAdapter;
    private LinearLayoutManager layoutManager;

    private FragmentMeallistBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMeallistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        return inflater.inflate(R.layout.fragment_home, container, false);

        // Sharing data
//        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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
        mealListPresenter = new MealListPresenterImpl(
                this,
                MealsRepositoryImpl.getInstance(
                        MealRemoteDataSourceImpl.getInstance(),
                        MealLocalDataSourceImpl.getInstance(getActivity())
                )
        );

        mealListPresenter.getAllMeals();
//        homePresenter.getPlannedMeals().observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
//            @Override
//            public void onChanged(List<Meal> meals) {
//                showData(meals);
//                Log.i(TAG, meals.toString());
//            }
//        });

        // UI
        recyclerView.setHasFixedSize(true);
        mealListAdapter = new MealListAdapter(this.getContext(), new ArrayList<Meal>(), this);
        layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mealListAdapter);
    }

    private void initUI() {
//        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycViewHome);
        recyclerView = (RecyclerView) binding.recycViewMealList;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }


    // implementation of HomeView methods

    @Override
    public void showData(List<Meal> meals) {
        mealListAdapter.setList(meals);
        mealListAdapter.notifyDataSetChanged();
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
        Toast.makeText(this.getContext(), meal.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddToFavClick(Meal meal) {
        mealListPresenter.addToFav(meal);
        mealListAdapter.notifyDataSetChanged();
        mealListPresenter.addMealToSunday(meal);
    }
}