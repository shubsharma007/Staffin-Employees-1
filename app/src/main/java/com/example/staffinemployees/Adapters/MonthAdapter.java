package com.example.staffinemployees.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.EventsByYearResponse;
import com.example.staffinemployees.Response.EventsMix;
import com.example.staffinemployees.Response.MyMonth;

import java.util.ArrayList;
import java.util.List;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.MyViewHolder> {
    Context context;
    List<EventsMix> eventsMixList;
    int jans = 0, febs = 0, marchs = 0, aprs = 0, mayis = 0, juns = 0, julys = 0, augs = 0, septs = 0, octs = 0, novs = 0, decs = 0;

    public MonthAdapter(Context context, List<EventsMix> eventsMixList) {
        this.context = context;
        this.eventsMixList = eventsMixList;

        for (EventsMix e : eventsMixList) {
            if (e.getMonth() == 1) {
                jans += 1;
            } else if (e.getMonth() == 2) {
                febs += 1;
            } else if (e.getMonth() == 3) {
                marchs += 1;
            } else if (e.getMonth() == 4) {
                aprs += 1;
            } else if (e.getMonth() == 5) {
                mayis += 1;
            } else if (e.getMonth() == 6) {
                juns += 1;
            } else if (e.getMonth() == 7) {
                julys += 1;
            } else if (e.getMonth() == 8) {
                augs += 1;
            } else if (e.getMonth() == 9) {
                septs += 1;
            } else if (e.getMonth() == 10) {
                octs += 1;
            } else if (e.getMonth() == 11) {
                novs += 1;
            } else if (e.getMonth() == 12) {
                decs += 1;
            }
        }
    }


    public void filterList(List<EventsMix> filterlist) {
        eventsMixList = filterlist;
        notifyDataSetChanged();

        jans = 0;
        febs = 0;
        marchs = 0;
        aprs = 0;
        mayis = 0;
        juns = 0;
        julys = 0;
        augs = 0;
        septs = 0;
        octs = 0;
        novs = 0;
        decs = 0;


        for (EventsMix e : eventsMixList) {
            if (e.getMonth() == 1) {
                jans += 1;
            } else if (e.getMonth() == 2) {
                febs += 1;
            } else if (e.getMonth() == 3) {
                marchs += 1;
            } else if (e.getMonth() == 4) {
                aprs += 1;
            } else if (e.getMonth() == 5) {
                mayis += 1;
            } else if (e.getMonth() == 6) {
                juns += 1;
            } else if (e.getMonth() == 7) {
                julys += 1;
            } else if (e.getMonth() == 8) {
                augs += 1;
            } else if (e.getMonth() == 9) {
                septs += 1;
            } else if (e.getMonth() == 10) {
                octs += 1;
            } else if (e.getMonth() == 11) {
                novs += 1;
            } else if (e.getMonth() == 12) {
                decs += 1;
            }
        }
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
        holder.recyclerView.setHasFixedSize(true);

        if (position == 0) {
            holder.monthTv.setText("January");
            Log.d("size of jans", String.valueOf(jans));
            if (jans > 0) {

                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.monthTv.setVisibility(View.VISIBLE);
//                holder.noEventFound.setVisibility(View.GONE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 1, eventsMixList));
            } else {
                holder.monthTv.setVisibility(View.GONE);
                holder.recyclerView.setVisibility(View.GONE);
//                holder.noEventFound.setVisibility(View.GONE);
            }

        } else if (position == 1) {
            holder.monthTv.setText("February");
            if (febs > 0) {
                holder.monthTv.setVisibility(View.VISIBLE);
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 2, eventsMixList));
            } else {
                holder.monthTv.setVisibility(View.GONE);
                holder.recyclerView.setVisibility(View.GONE);
            }
        } else if (position == 2) {
            holder.monthTv.setText("march");
            if (marchs > 0) {
                holder.monthTv.setVisibility(View.VISIBLE);
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 3, eventsMixList));
            } else {
                holder.monthTv.setVisibility(View.GONE);
                holder.recyclerView.setVisibility(View.GONE);
            }
        } else if (position == 3) {
            holder.monthTv.setText("April");
            if (aprs > 0) {
                holder.monthTv.setVisibility(View.VISIBLE);
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 4, eventsMixList));
            } else {
                holder.monthTv.setVisibility(View.GONE);
                holder.recyclerView.setVisibility(View.GONE);
            }
        } else if (position == 4) {
            holder.monthTv.setText("May");
            if (mayis > 0) {
                holder.monthTv.setVisibility(View.VISIBLE);
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 5, eventsMixList));
            } else {
                holder.monthTv.setVisibility(View.GONE);
                holder.recyclerView.setVisibility(View.GONE);
            }
        } else if (position == 5) {
            holder.monthTv.setText("June");
            if (juns > 0) {
                holder.monthTv.setVisibility(View.VISIBLE);
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 6, eventsMixList));
            } else {
                holder.monthTv.setVisibility(View.GONE);
                holder.recyclerView.setVisibility(View.GONE);
            }
        } else if (position == 6) {
            holder.monthTv.setText("July");
            if (julys > 0) {
                holder.monthTv.setVisibility(View.VISIBLE);
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 7, eventsMixList));
            } else {
                holder.monthTv.setVisibility(View.GONE);
                holder.recyclerView.setVisibility(View.GONE);
            }
        } else if (position == 7) {
            holder.monthTv.setText("August");
            if (augs > 0) {
                holder.monthTv.setVisibility(View.VISIBLE);
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 8, eventsMixList));
            } else {
                holder.monthTv.setVisibility(View.GONE);
                holder.recyclerView.setVisibility(View.GONE);
            }
        } else if (position == 8) {
            holder.monthTv.setText("September");
            if (septs > 0) {
                holder.monthTv.setVisibility(View.VISIBLE);
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 9, eventsMixList));
            } else {
                holder.monthTv.setVisibility(View.GONE);
                holder.recyclerView.setVisibility(View.GONE);
            }
        } else if (position == 9) {
            holder.monthTv.setText("October");
            if (octs > 0) {
                holder.monthTv.setVisibility(View.VISIBLE);
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 10, eventsMixList));
            } else {
                holder.monthTv.setVisibility(View.GONE);
                holder.recyclerView.setVisibility(View.GONE);
            }
        } else if (position == 10) {
            holder.monthTv.setText("November");
            if (novs > 0) {
                holder.monthTv.setVisibility(View.VISIBLE);
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 11, eventsMixList));
            } else {
                holder.monthTv.setVisibility(View.GONE);
                holder.recyclerView.setVisibility(View.GONE);
            }
        } else if (position == 11) {
            holder.monthTv.setText("December");
            if (decs > 0) {
                holder.monthTv.setVisibility(View.VISIBLE);
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 12, eventsMixList));
            } else {
                holder.monthTv.setVisibility(View.GONE);
                holder.recyclerView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return 12;
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
