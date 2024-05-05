package com.example.myweatherbase.activities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class RecycleView extends RecyclerView.Adapter<RecycleView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<com.example.myweatherbase.activities.model.List> infoList;
    private View.OnClickListener onClickListener;
    private Root root;

    public RecycleView(Context context, Root root){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.root = root;
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.simple_element,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        com.example.myweatherbase.activities.model.List forecast = root.list.get(position);
        ImageDownloader.downloadImage(Parameters.ICON_URL_PRE + forecast.weather.get(0).icon + Parameters.ICON_URL_POST, holder.weatherImage);
        String weather = forecast.weather.get(0).description;
        String weatherDesc = weather.substring(0,1).toUpperCase()+weather.substring(1);
        holder.textWeather.setText(weatherDesc.length()>8?weatherDesc.substring(0,8)+"...":weatherDesc);
        holder.textMin.setText("Min " + forecast.main.temp_min);
        holder.textMax.setText("Max " + forecast.main.temp_max);

        Date date = new Date((long)forecast.dt*1000);
        SimpleDateFormat dateDayOfWeek = new SimpleDateFormat("EEE");
        holder.textDay.setText(writeDayOfWeek(dateDayOfWeek.format(date))) ;
        SimpleDateFormat dateDay = new SimpleDateFormat("d/MM/yy");
        holder.textDate.setText(dateDay.format(date));
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm");
        holder.textHour.setText(hour.format(date));
    }

    @Override
    public int getItemCount() {
        return root.list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView weatherImage;
        private TextView textDay, textWeather, textMax, textMin, textHour, textDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weatherImage = itemView.findViewById(R.id.imageWeather);
            textDay = itemView.findViewById(R.id.textDay);
            textDate = itemView.findViewById(R.id.textDate);
            textWeather = itemView.findViewById(R.id.textWeather);
            textMax = itemView.findViewById(R.id.textMax);
            textMin = itemView.findViewById(R.id.textMin);
            textHour = itemView.findViewById(R.id.textHour);
            textDate = itemView.findViewById(R.id.textDate);
        }
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
