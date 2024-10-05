package com.example.dishdiary.features.meal_details.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dishdiary.R;
import com.example.dishdiary.datasources.db.MealLocalDataSourceImpl;
import com.example.dishdiary.datasources.network.MealRemoteDataSourceImpl;
import com.example.dishdiary.features.meal_details.presenter.MealDetailsPresenter;
import com.example.dishdiary.features.meal_details.presenter.MealDetailsPresenterImpl;
import com.example.dishdiary.features.meal_list.presenter.MealListPresenterImpl;
import com.example.dishdiary.model.Meal;
import com.example.dishdiary.model.MealsRepositoryImpl;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dishdiary.databinding.ActivityMealDetailsBinding;

import java.util.List;

public class MealDetailsActivity extends AppCompatActivity implements MealDetailsView, OnMealDetailsClickListener {

    private ActivityMealDetailsBinding binding;

    private MealDetailsPresenter mealDetailsPresenter;

    private ImageView imgView;
    private TextView txtName, txtCategory, txtArea, txtInstructions;
    private Button btnFav;
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMealDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Presenter
        mealDetailsPresenter = new MealDetailsPresenterImpl(
                this,
                MealsRepositoryImpl.getInstance(
                        MealRemoteDataSourceImpl.getInstance(),
                        MealLocalDataSourceImpl.getInstance(this)
                )
        );

        // UI - Floating Favourite Button
        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", this)
                        .setAnchorView(R.id.fab).show();
            }
        });

        // UI - Meal Details Components
        imgView = binding.imgView;
        txtCategory = binding.scroll.txtCategory;
        txtArea = binding.scroll.txtArea;
        webView = binding.scroll.webView;


        Meal meal = (Meal) getIntent().getSerializableExtra("mealDetails");
        if(meal != null) {
            mealDetailsPresenter.getMealById(meal.getIdMeal());

        }
    }

    private String getEmbedUrl(String url) {
//        String videoId = null;
//        videoId = url.substring(url.indexOf("="));
//        return "https://www.youtube.com/embed/" + videoId + "?autoplay=0";
        return url.replace("https://www.youtube.com/watch?v=", "https://www.youtube.com/embed/") +
                (url.contains("&") ? url.substring(url.indexOf("&")) : "");
    }

    @Override
    public void showData(Meal meal) {

        // UI - Toolbar
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;

        toolBarLayout.setTitle(meal.getStrMeal());
        toolbar.setTitleTextAppearance(this, R.style.Theme_DishDiary);
        txtCategory.setText(meal.getStrCategory());
        txtArea.setText(meal.getStrArea());

        Glide.with(this).load(meal.getStrMealThumb())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground))
                .into(imgView);

        // Video Web View
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl(getEmbedUrl(meal.getStrYoutube()));
    }

    @Override
    public void showErrMsg(String error) {

    }

    @Override
    public void onLayoutClick(Meal meal) {

    }

    @Override
    public void onAddToFavClick(Meal meal) {

    }

    @Override
    public void onRemoveFromFavClick(Meal meal) {

    }
}