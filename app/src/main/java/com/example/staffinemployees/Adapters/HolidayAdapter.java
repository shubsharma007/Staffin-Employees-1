package com.example.staffinemployees.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.TintTypedArray;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.AllHolidays;
import com.example.staffinemployees.Response.NationalCreatedMix;

import java.util.Arrays;
import java.util.List;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.HolidayViewHolder> {
    Context context;
    List<NationalCreatedMix> holidays;

    public HolidayAdapter(Context context, List<NationalCreatedMix> holidays) {
        this.holidays = holidays;
        this.context = context;
    }

    @NonNull
    @Override
    public HolidayAdapter.HolidayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.holiday_layout, parent, false);
        return new HolidayAdapter.HolidayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayAdapter.HolidayViewHolder holder, int position) {

        Animation animationLeft = AnimationUtils.loadAnimation(holder.card2.getContext(), android.R.anim.slide_in_left);
        holder.card1.startAnimation(animationLeft);
        holder.card2.startAnimation(animationLeft);
        holder.titleTv.startAnimation(animationLeft);
        holder.dateTv.startAnimation(animationLeft);
        holder.dayTv.startAnimation(animationLeft);
        holder.descriptionTv.startAnimation(animationLeft);


        NationalCreatedMix singleUnit = holidays.get(position);
        holder.titleTv.setText(singleUnit.getName());
        String[] tempDate = singleUnit.getDate().split("-");
        holder.dateTv.setText(tempDate[2]);
        String din = tempDate[1];

        switch (din) {
            case "01":
                holder.dayTv.setText("Jan");
                break;
            case "02":
                holder.dayTv.setText("Feb");
                break;
            case "03":
                holder.dayTv.setText("Mar");
                break;
            case "04":
                holder.dayTv.setText("Apr");
                break;
            case "05":
                holder.dayTv.setText("May");
                break;
            case "06":
                holder.dayTv.setText("Jun");
                break;
            case "07":
                holder.dayTv.setText("July");
                break;
            case "08":
                holder.dayTv.setText("Aug");
                break;
            case "09":
                holder.dayTv.setText("Sep");
                break;
            case "10":
                holder.dayTv.setText("Oct");
                break;
            case "11":
                holder.dayTv.setText("Nov");
                break;
            case "12":
                holder.dayTv.setText("Dec");
                break;

        }
//        holder.dayTv.setText(singleUnit.getHoliday_day());
        holder.descriptionTv.setText(singleUnit.getDesc());
        if (position % 3 == 0) {
            holder.ll2.setBackgroundResource(R.color.mainColor);
        } else if (position % 5 == 0) {
            holder.ll2.setBackgroundResource(R.color.yellow);
        } else if (position % 2 == 0) {
            holder.ll2.setBackgroundResource(R.color.pink);
        } else {
            holder.ll2.setBackgroundResource(R.color.green);
        }
    }

    @Override
    public int getItemCount() {
        return holidays.size();
    }

    public class HolidayViewHolder extends RecyclerView.ViewHolder {
        CardView card1, card2;
        LinearLayout ll2;
        TextView dateTv, dayTv, titleTv, descriptionTv;

        public HolidayViewHolder(@NonNull View itemView) {
            super(itemView);
            card1 = itemView.findViewById(R.id.card1);
            card2 = itemView.findViewById(R.id.card2);
            ll2 = itemView.findViewById(R.id.ll2);
            dateTv = itemView.findViewById(R.id.dateTv);
            dayTv = itemView.findViewById(R.id.dayTv);
            titleTv = itemView.findViewById(R.id.titleTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
        }
    }
}
