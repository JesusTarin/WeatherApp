package com.example.myweatherbase.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.City;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.BaseActivity;
import com.example.myweatherbase.base.CallInterface;

public class ForecastsActivity extends BaseActivity implements CallInterface {

    private TextView cityName;
    private RecyclerView forecasts;
    private Root root;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecasts);
        cityName = findViewById(R.id.cityName);
        cityName.setText(cityName.getText()+((MainActivity.City) getIntent().getExtras().get("city")).getName());
        forecasts = findViewById(R.id.recyclerForecasts);

        showProgress();
        executeCall(this);

    }


    @Override
    public void doInBackground() {
        root = Connector.getConector().get(Root.class,"&lat=" + ((MainActivity.City) getIntent().getExtras().get("city")).getLat() +"&lon=" + ((MainActivity.City) getIntent().getExtras().get("city")).getLon());
    }

    @Override
    public void doInUI() {
        hideProgress();
        RecycleView recycleView = new RecycleView(this, root);
        forecasts.setAdapter(recycleView);
        if (this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE)
            forecasts.setLayoutManager(new GridLayoutManager(this,2));
        else
            forecasts.setLayoutManager(new LinearLayoutManager(this));
    }
}
