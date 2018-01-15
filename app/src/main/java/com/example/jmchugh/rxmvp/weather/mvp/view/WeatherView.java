package com.example.jmchugh.rxmvp.weather.mvp.view;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.jmchugh.rxmvp.R;
import com.example.jmchugh.rxmvp.RxMVPApplication;
import com.example.jmchugh.rxmvp.weather.dagger.DaggerWeatherComponent;
import com.example.jmchugh.rxmvp.weather.dagger.WeatherModule;
import com.example.jmchugh.rxmvp.weather.mvp.model.entity.Forecast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ME on 1/12/2018.
 */

public class WeatherView extends FrameLayout {

    @BindView(R.id.weather_forecast_recycler_view)
    RecyclerView recyclerView;

    @Inject
    WeatherRecyclerAdapter weatherRecyclerAdapter;

    @Inject
    RecyclerView.LayoutManager linearLayoutManger;

    public WeatherView(Activity activity) {
        super(activity);

        DaggerWeatherComponent.builder()
                .appComponent(RxMVPApplication.get(activity).component())
                .weatherModule(new WeatherModule(activity))
                .build().inject(this);

        inflate(getContext(), R.layout.activity_main, this);

        ButterKnife.bind(this);

        recyclerView.setAdapter(weatherRecyclerAdapter);
        recyclerView.setLayoutManager(linearLayoutManger);
    }

    public void updateWeatherView(List<Forecast> forecasts){
        
        weatherRecyclerAdapter.setWeatherForecasts(forecasts);
    }
    
    public void showErrorToast(){

        Toast.makeText(getContext(), "An error has occured.", Toast.LENGTH_SHORT).show();
    }
}
