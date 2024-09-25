package com.example.dishdiary.home.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdiary.databinding.FragmentHomeBinding;
import com.example.dishdiary.home.controller.HomePresenter;
import com.example.dishdiary.model.Meal;

import java.util.List;

import com.example.dishdiary.home.controller.HomePresenter;
import com.example.dishdiary.home.controller.HomePresenterImpl;
import com.example.dishdiary.model.MealsRepositoryImpl;
import com.example.dishdiary.network.MealRemoteDataSourceImpl;
import com.example.dishdiary.R;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.db.MealLocalDataSourceImpl;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnHomeClickListener, HomeView{

    private static final String TAG = "HomeFragment";

    // Presenter
    HomePresenter homePresenter;

    // UI
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private LinearLayoutManager layoutManager;

    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
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
        homePresenter = new HomePresenterImpl(
                this,
                MealsRepositoryImpl.getInstance(
                        MealRemoteDataSourceImpl.getInstance(),
                        MealLocalDataSourceImpl.getInstance(getActivity())
                )
        );

        homePresenter.getAllMeals();
//        homePresenter.getPlannedMeals().observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
//            @Override
//            public void onChanged(List<Meal> meals) {
//                showData(meals);
//                Log.i(TAG, meals.toString());
//            }
//        });

        // UI
        recyclerView.setHasFixedSize(true);
        homeAdapter = new HomeAdapter(this.getContext(), new ArrayList<Meal>(), this);
        layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeAdapter);
    }

    private void initUI() {
//        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycViewHome);
        recyclerView = (RecyclerView) binding.recycViewHome;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }


    // implementation of HomeView methods

    @Override
    public void showData(List<Meal> meals) {
        homeAdapter.setList(meals);
        homeAdapter.notifyDataSetChanged();
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
        homePresenter.addToFav(meal);
        homeAdapter.notifyDataSetChanged();
        homePresenter.addMealToSunday(meal);
    }
}