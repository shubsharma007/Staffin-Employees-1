package com.example.staffinemployees.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffinemployees.R;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.HolidayViewHolder> {
    Context context;

    public HolidayAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public HolidayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.holiday_layout, parent, false);
        return new HolidayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayViewHolder holder, int position) {
        if (position % 3 == 0) {
            holder.holidayName.setText("Ram Navami");
            holder.holidayTime.setText("5AM TO 7PM");
            holder.holidayDate.setText("30");
            holder.holidayDay.setText("Fri");
            holder.ll2.setBackgroundResource(R.color.mainColor);
        } else if (position % 4 == 0) {
            holder.holidayName.setText("Hanuman Janmotsav");
            holder.holidayTime.setText("5AM TO 7PM");
            holder.holidayDate.setText("6");
            holder.holidayDay.setText("Tue");
            holder.ll2.setBackgroundResource(R.color.yellow);
        } else if (position % 2 == 0) {
            holder.holidayName.setText("Republic day");
            holder.holidayTime.setText("5AM TO 7PM");
            holder.holidayDate.setText("26");
            holder.holidayDay.setText("Fri");
            holder.ll2.setBackgroundResource(R.color.pink);
        } else if (position % 5 == 0) {
            holder.holidayName.setText("Independence Day");
            holder.holidayTime.setText("5AM TO 7PM");
            holder.holidayDate.setText("15");
            holder.holidayDay.setText("Mon");
            holder.ll2.setBackgroundResource(R.color.green);
        } else if (position % 1 == 0) {
            holder.holidayName.setText("Diwali");
            holder.holidayTime.setText("5AM TO 7PM");
            holder.holidayDate.setText("27");
            holder.holidayDay.setText("Sun");
            holder.ll2.setBackgroundResource(R.color.green);
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class HolidayViewHolder extends RecyclerView.ViewHolder {
        CardView card2;
        LinearLayout ll2;
        TextView holidayName, holidayTime, holidayDate, holidayDay;

        public HolidayViewHolder(@NonNull View itemView) {
            super(itemView);
            card2 = itemView.findViewById(R.id.card2);
            ll2 = itemView.findViewById(R.id.ll2);
            holidayName = itemView.findViewById(R.id.holidayName);
            holidayTime = itemView.findViewById(R.id.holidayTime);
            holidayDate = itemView.findViewById(R.id.holidayDate);
            holidayDay = itemView.findViewById(R.id.holidayDay);
        }
    }
}
