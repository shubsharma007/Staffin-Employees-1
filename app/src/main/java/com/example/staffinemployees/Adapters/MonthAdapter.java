package com.example.staffinemployees.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.MyMonth;

import java.util.ArrayList;
import java.util.List;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MyViewHolder> {
    Context context;
    List<MyMonth> my;

    List<String> months;

    public MonthAdapter(List<MyMonth> my, Context context) {
        this.context = context;
        this.my = my;
        months = new ArrayList<>();
        months.add("january");
        months.add("february");
        months.add("march");
        months.add("april");
        months.add("may");
        months.add("june");
        months.add("july");
        months.add("august");
        months.add("september");
        months.add("october");
        months.add("november");
        months.add("december");
    }

    public void filterList(List<MyMonth> filterlist) {
        my = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MonthAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_month_event_layout, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MonthAdapter.MyViewHolder holder, int position) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(new EventAdapter(context));
        holder.monthTv.setText(months.get(position));
    }

    @Override
    public int getItemCount() {
        return months.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        TextView monthTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            monthTv = itemView.findViewById(R.id.monthTv);
            recyclerView = itemView.findViewById(R.id.EventRv);

        }
    }
}
