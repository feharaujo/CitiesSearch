package com.fearaujo.citysearch.main.items;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fearaujo.citysearch.main.SelectCityCallback;
import com.fearaujo.model.City;

import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private final List<City> cities = new ArrayList<>();
    private final SelectCityCallback mCallback;

    public ItemsAdapter(SelectCityCallback callback) {
        this.mCallback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                android.R.layout.simple_list_item_1, parent, false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void update(List<City> cities) {
        this.cities.clear();
        this.cities.addAll(cities);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }

        public void bind(final City city) {
            textView.setText(city.getFullName());
            textView.setOnClickListener(v -> mCallback.onSelect(city));
        }
    }

}
