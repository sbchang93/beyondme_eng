package com.example.fragmentexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fragmentexample.R;
import com.example.fragmentexample.model.weather.Weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherAdapter extends BaseAdapter {
    private final List<Weather> mData;

    private Map<String, Integer> mWeatherImageMap;


    // List 를 구현한 모든 것(ArrayList 등) 을 받는 생성자
    public WeatherAdapter(List<Weather> data) {
        mData = data;

        mWeatherImageMap = new HashMap<>();
        mWeatherImageMap.put("맑음", R.drawable.sunny);
        mWeatherImageMap.put("폭설", R.drawable.blizzard);
        mWeatherImageMap.put("구름", R.drawable.cloudy);
        mWeatherImageMap.put("비", R.drawable.rainy);
        mWeatherImageMap.put("눈", R.drawable.snow);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);

            holder = new ViewHolder();
            holder.weatherImage = convertView.findViewById(R.id.weather_image);
            holder.city = convertView.findViewById(R.id.city_text);
            holder.temperature = convertView.findViewById(R.id.temperature_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Weather weather = (Weather) getItem(position);
        holder.weatherImage.setImageResource(mWeatherImageMap.get(weather.getWeather()));
        holder.city.setText(weather.getCountry());
        holder.temperature.setText(weather.getTemperature());

        return convertView;
    }

    // 뷰 홀더 패턴
    static class ViewHolder {
        ImageView weatherImage;
        TextView city;
        TextView temperature;
    }
}