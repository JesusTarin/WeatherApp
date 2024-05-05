package com.example.myweatherbase.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.List;
import com.example.myweatherbase.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ForecastsCompleteActivity extends BaseActivity {

    private TextView day;
    private TextView date;
    private TextView hour;
    private TextView weather;
    private TextView max;
    private TextView min;
    private TextView temp;
    private TextView feelsLike;
    private TextView humidity;
    private TextView wind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecasts_complete);

        day = findViewById(R.id.textCDay);
        date = findViewById(R.id.textCDate);
        hour = findViewById(R.id.textCHour);
        weather = findViewById(R.id.textCWeather);
        max = findViewById(R.id.textCMax);
        min = findViewById(R.id.textCMin);
        temp = findViewById(R.id.textCTemp);
        feelsLike = findViewById(R.id.textCFL);
        humidity = findViewById(R.id.textCHumidity);
        wind = findViewById(R.id.textCWind);


        com.example.myweatherbase.activities.model.List forecast = (List) getIntent().getExtras().get("forecast");
        String weatherString = forecast.weather.get(0).description;
        String weatherDesc = weatherString.substring(0,1).toUpperCase()+weatherString.substring(1);
        weather.setText(weatherDesc);
        min.setText("Minimum: " + forecast.main.temp_min + " °C");
        max.setText("Maximum: " + forecast.main.temp_max + " °C");
        temp.setText("Temperature: " + forecast.main.temp + " °C");
        feelsLike.setText("Feels like: " + forecast.main.feels_like + " °C");
        humidity.setText("Humidity: " + forecast.main.humidity + "%");
        wind.setText("Wind: " + forecast.wind.speed + " KM/H");

        Date dateInfo = new Date((long)forecast.dt*1000);
        SimpleDateFormat dateDayOfWeek = new SimpleDateFormat("EEE");
        day.setText(writeDayOfWeek(dateDayOfWeek.format(dateInfo))) ;
        SimpleDateFormat dateDay = new SimpleDateFormat("dd/MM/yy");
        date.setText(dateDay.format(dateInfo));
        SimpleDateFormat hourInfo = new SimpleDateFormat("HH:mm");
        hour.setText(hourInfo.format(dateInfo));



    }
    private static String writeDayOfWeek(String shortDay) {
        switch (shortDay) {
            case "Tue":
                return "Tuesday";
            case "Wed":
                return "Wednesday";
            case "Thu":
                return "Thursday";
            case "Fri":
                return "Friday";
            case "Sat":
                return "Saturday";
            case "Sun":
                return "Sunday";
            default:
                return "Monday";
        }
    }
}
