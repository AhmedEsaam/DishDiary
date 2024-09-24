package com.example.dishdiary.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dishdiary.databinding.FragmentHomeBinding;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.ui.dashboard.DashboardViewModel;

import java.util.List;

public class HomeFragment extends Fragment implements HomeView{


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;

//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        ///
        DashboardViewModel dashboardViewModel = new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);

//        final Button btn = binding.btn;
//        btn.setOnClickListener(view -> dashboardViewModel.setText(txtDash.getText().toString()));


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showData(List<Meal> meals) {

    }

    @Override
    public void showErrMsg(String error) {

    }
}