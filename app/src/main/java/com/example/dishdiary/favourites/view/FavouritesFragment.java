package com.example.dishdiary.favourites.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdiary.databinding.FragmentSavedBinding;
import com.example.dishdiary.db.MealLocalDataSourceImpl;
import com.example.dishdiary.favourites.presenter.FavouritesPresenter;
import com.example.dishdiary.favourites.presenter.FavouritesPresenterImpl;
import com.example.dishdiary.favourites.view.FavouritesAdapter;
import com.example.dishdiary.favourites.view.FavouritesView;
import com.example.dishdiary.favourites.view.OnFavouritesClickListener;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepositoryImpl;
import com.example.dishdiary.network.MealRemoteDataSourceImpl;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment implements OnFavouritesClickListener, FavouritesView {

    private FragmentSavedBinding binding;

    private static final String TAG = "FavouritesFragment";

    // Presenter
    FavouritesPresenter favouritesPresenter;

    // UI
    private RecyclerView recyclerView;
    private FavouritesAdapter favouritesAdapter;
    private LinearLayoutManager layoutManager;

    LiveData<List<Meal>> favouriteMeals;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavouritesViewModel favouritesViewModel =
                new ViewModelProvider(this).get(FavouritesViewModel.class);

        binding = FragmentSavedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textNotifications;
//        favouritesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();

        // Presenter
        favouritesPresenter = new FavouritesPresenterImpl(
                this,
                MealsRepositoryImpl.getInstance(
                        MealRemoteDataSourceImpl.getInstance(),
                        MealLocalDataSourceImpl.getInstance(getActivity())
                )
        );

        // UI
        recyclerView.setHasFixedSize(true);
        favouritesAdapter = new FavouritesAdapter(this.getContext(), new ArrayList<Meal>(), this);
        layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(favouritesAdapter);

        // Observe on data
        favouriteMeals = favouritesPresenter.getFavMeals();

        favouriteMeals.observe(getViewLifecycleOwner(), this::showData);

    }

    private void initUI() {
        recyclerView = (RecyclerView) binding.recycViewFav;
    }


    // implementation of HomeView methods

    @Override
    public void showData(List<Meal> meals) {
        favouritesAdapter.setList(meals);
        favouritesAdapter.notifyDataSetChanged();
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
    public void onRemoveFromFavClick(Meal meal) {
        favouritesPresenter.removeFromFav(meal);
        favouritesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}