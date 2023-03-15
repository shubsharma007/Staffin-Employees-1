package com.example.staffinemployees.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffinemployees.R;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.HolidayViewHolder> {
    Context context;
    public HolidayAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public HolidayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.holiday_layout,parent,false);
        return new HolidayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayViewHolder holder, int position) {
        if(position%3==0)
        {
            holder.ll2.setBackgroundResource(R.color.mainColor);
        }
        else if(position%5==0)
        {
            holder.ll2.setBackgroundResource(R.color.yellow);
        }
        else if(position%2==0)
        {
            holder.ll2.setBackgroundResource(R.color.pink);
        }
        else
        {
            holder.ll2.setBackgroundResource(R.color.green);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class HolidayViewHolder extends RecyclerView.ViewHolder {
        CardView card2;
        LinearLayout ll2;
        public HolidayViewHolder(@NonNull View itemView) {
            super(itemView);
            card2=itemView.findViewById(R.id.card2);
            ll2=itemView.findViewById(R.id.ll2);
        }
    }
}
