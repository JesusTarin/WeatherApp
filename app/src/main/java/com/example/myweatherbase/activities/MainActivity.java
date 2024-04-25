package com.example.myweatherbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private Spinner cities;
    private ImageView cityImage;
    private Button forecast;
    private Root root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cities = findViewById(R.id.spinnerCities);
        cityImage = findViewById(R.id.imageCity);
        forecast = findViewById(R.id.buttonForecast);
        Intent intent = new Intent(this, ForecastsActivity.class);

        cities.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, City.values()));

        String selectedCity = "";
        cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        intent.putExtra("city", City.VALENCIA);
                        cityImage.setImageResource(R.mipmap.ic_valencia_foreground);
                        break;
                    case 1:
                        intent.putExtra("city", City.MADRID);
                        cityImage.setImageResource(R.mipmap.ic_madrid_foreground);
                        break;
                    case 2:
                        intent.putExtra("city", City.BARCELONA);
                        cityImage.setImageResource(R.mipmap.ic_barcelona_foreground);
                        break;
                    case 3:
                        intent.putExtra("city", City.BILBAO);
                        cityImage.setImageResource(R.mipmap.ic_bilbao_foreground);
                        break;
                    case 4:
                        intent.putExtra("city", City.ALBACETE);
                        cityImage.setImageResource(R.mipmap.ic_albacete_foreground);
                        break;
                    case 5:
                        intent.putExtra("city", City.LEON);
                        cityImage.setImageResource(R.mipmap.ic_leon_foreground);
                    case 6:
                        intent.putExtra("city", City.LUGO);
                        cityImage.setImageResource(R.mipmap.ic_lugo_foreground);
                        break;
                    case 7:
                        intent.putExtra("city", City.VALLADOLID);
                        cityImage.setImageResource(R.mipmap.ic_valladolid_foreground);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        forecast.setOnClickListener(view -> {
            intent.putExtra("lat",selectedCity);
            startActivity(intent);
        });
    }

    public enum City {
        VALENCIA("39.4078888", "-0.4439117", "Valencia"),
        MADRID("40.4377968", "-3.9913481", "Madrid"),
        BARCELONA("41.3927673", "2.0577888", "Barcelona"),
        BILBAO("43.2633529", "-2.9541639", "Bilbao"),
        ALBACETE("38.9922307", "-1.8811889", "Albacete"),
        LEON("42.6036354", "-5.5979908", "León"),
        LUGO("43.0123132", "-7.5770996", "Lugo"),
        VALLADOLID("41.779318", "-4.747171", "Valladolid");

        private String lat;
        private String lon;
        private String name;

        City(String lat, String lon, String name) {
            this.lat = lat;
            this.lon = lon;
            this.name = name;
        }

        public String getLat() {
            return lat;
        }
        public String getLon() {
            return lon;
        }
        public String getName() {
            return name;
        }
    }
}