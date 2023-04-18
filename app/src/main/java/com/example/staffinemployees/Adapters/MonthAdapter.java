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

//    public MonthAdapter(Context context, EventsByYearResponse.EventDetails eventDetails) {
//        this.context = context;
//        this.eventDetails = eventDetails;
//        monthAvailable = new ArrayList<>();
//        januaries = new ArrayList<>();
//        februaries = new ArrayList<>();
//        marches = new ArrayList<>();
//        aprils = new ArrayList<>();
//        mays = new ArrayList<>();
//        junes = new ArrayList<>();
//        julies = new ArrayList<>();
//        augusts = new ArrayList<>();
//        septembers = new ArrayList<>();
//        octobers = new ArrayList<>();
//        novembers = new ArrayList<>();
//        decembers = new ArrayList<>();
//
//        if (eventDetails.getJanuary() != null && eventDetails.getJanuary().size() > 0) {
//            monthAvailable.add("January");
//            januaries = eventDetails.getJanuary();
//        }
//        if (eventDetails.getFebruary() != null && eventDetails.getFebruary().size() > 0) {
//            monthAvailable.add("February");
//            februaries = eventDetails.getFebruary();
//        }
//        if (eventDetails.getMarch() != null && eventDetails.getMarch().size() > 0) {
//            monthAvailable.add("March");
//            marches = eventDetails.getMarch();
//        }
//        if (eventDetails.getApril() != null && eventDetails.getApril().size() > 0) {
//            monthAvailable.add("April");
//            aprils = eventDetails.getApril();
//        }
//        if (eventDetails.getMay() != null && eventDetails.getMay().size() > 0) {
//            monthAvailable.add("May");
//            mays = eventDetails.getMay();
//        }
//        if (eventDetails.getJune() != null && eventDetails.getJune().size() > 0) {
//            monthAvailable.add("June");
//            junes = eventDetails.getJune();
//        }
//        if (eventDetails.getJuly() != null && eventDetails.getJuly().size() > 0) {
//            monthAvailable.add("July");
//            julies = eventDetails.getJuly();
//        }
//        if (eventDetails.getAugust() != null && eventDetails.getAugust().size() > 0) {
//            monthAvailable.add("August");
//            augusts = eventDetails.getAugust();
//        }
//        if (eventDetails.getSeptember() != null && eventDetails.getSeptember().size() > 0) {
//            monthAvailable.add("September");
//            septembers = eventDetails.getSeptember();
//        }
//        if (eventDetails.getOctober() != null && eventDetails.getOctober().size() > 0) {
//            monthAvailable.add("October");
//            octobers = eventDetails.getOctober();
//        }
//        if (eventDetails.getNovember() != null && eventDetails.getNovember().size() > 0) {
//            monthAvailable.add("November");
//            novembers = eventDetails.getNovember();
//        }
//        if (eventDetails.getDecember() != null && eventDetails.getDecember().size() > 0) {
//            monthAvailable.add("December");
//            decembers = eventDetails.getDecember();
//        }
//
//    }

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
            if (jans != 0) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.noEventFound.setVisibility(View.GONE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 1, eventsMixList));
            } else {
                holder.recyclerView.setVisibility(View.GONE);
                holder.noEventFound.setVisibility(View.VISIBLE);
            }

//            if (!(januaries.size() > 0)) {
//                holder.recyclerView.setVisibility(View.GONE);
//                holder.noEventFound.setVisibility(View.VISIBLE);
//            } else {
//                holder.noEventFound.setVisibility(View.GONE);
//                holder.recyclerView.setVisibility(View.VISIBLE);
//            }
//            holder.recyclerView.setAdapter(new EventAdapter(context, januaries, null, null, null, null, null, null, null, null, null, null, null));
        } else if (position == 1) {
            holder.monthTv.setText("February");
            if (febs > 0) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.noEventFound.setVisibility(View.GONE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 2, eventsMixList));

//                holder.recyclerView.setAdapter(new EventAdapter(context,));
            } else {
                holder.recyclerView.setVisibility(View.GONE);
                holder.noEventFound.setVisibility(View.VISIBLE);
            }
//            if (!(februaries.size() > 0)) {
//                holder.recyclerView.setVisibility(View.GONE);
//                holder.noEventFound.setVisibility(View.VISIBLE);
//            } else {
//                holder.noEventFound.setVisibility(View.GONE);
//                holder.recyclerView.setVisibility(View.VISIBLE);
//            }
//            holder.recyclerView.setAdapter(new EventAdapter(context, null, februaries, null, null, null, null, null, null, null, null, null, null));
        } else if (position == 2) {
            holder.monthTv.setText("march");
            if (marchs > 0) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.noEventFound.setVisibility(View.GONE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 3, eventsMixList));

//                holder.recyclerView.setAdapter(new EventAdapter(context,));
            } else {
                holder.recyclerView.setVisibility(View.GONE);
                holder.noEventFound.setVisibility(View.VISIBLE);
            }
//            if (!(marches.size() > 0)) {
//                holder.recyclerView.setVisibility(View.GONE);
//                holder.noEventFound.setVisibility(View.VISIBLE);
//            } else {
//                holder.noEventFound.setVisibility(View.GONE);
//                holder.recyclerView.setVisibility(View.VISIBLE);
//            }
//            holder.recyclerView.setAdapter(new EventAdapter(context, null, null, marches, null, null, null, null, null, null, null, null, null));
        } else if (position == 3) {
            holder.monthTv.setText("April");
            if (aprs > 0) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.noEventFound.setVisibility(View.GONE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 4, eventsMixList));

//                holder.recyclerView.setAdapter(new EventAdapter(context,));
            } else {
                holder.recyclerView.setVisibility(View.GONE);
                holder.noEventFound.setVisibility(View.VISIBLE);
            }
//            if (!(aprils.size() > 0)) {
//                holder.recyclerView.setVisibility(View.GONE);
//                holder.noEventFound.setVisibility(View.VISIBLE);
//            } else {
//                holder.noEventFound.setVisibility(View.GONE);
//                holder.recyclerView.setVisibility(View.VISIBLE);
//            }
//            holder.recyclerView.setAdapter(new EventAdapter(context, null, null, null, aprils, null, null, null, null, null, null, null, null));
        } else if (position == 4) {
            holder.monthTv.setText("May");
            if (mayis > 0) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.noEventFound.setVisibility(View.GONE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 5, eventsMixList));

//                holder.recyclerView.setAdapter(new EventAdapter(context,));
            } else {
                holder.recyclerView.setVisibility(View.GONE);
                holder.noEventFound.setVisibility(View.VISIBLE);
            }
//            if (!(mays.size() > 0)) {
//                holder.recyclerView.setVisibility(View.GONE);
//                holder.noEventFound.setVisibility(View.VISIBLE);
//            } else {
//                holder.noEventFound.setVisibility(View.GONE);
//                holder.recyclerView.setVisibility(View.VISIBLE);
//            }
//            holder.recyclerView.setAdapter(new EventAdapter(context, null, null, null, null, mays, null, null, null, null, null, null, null));
        } else if (position == 5) {
            holder.monthTv.setText("June");
            if (juns > 0) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.noEventFound.setVisibility(View.GONE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 6, eventsMixList));

//                holder.recyclerView.setAdapter(new EventAdapter(context,));
            } else {
                holder.recyclerView.setVisibility(View.GONE);
                holder.noEventFound.setVisibility(View.VISIBLE);
            }
//            if (!(junes.size() > 0)) {
//                holder.recyclerView.setVisibility(View.GONE);
//                holder.noEventFound.setVisibility(View.VISIBLE);
//            } else {
//                holder.noEventFound.setVisibility(View.GONE);
//                holder.recyclerView.setVisibility(View.VISIBLE);
//            }
//            holder.recyclerView.setAdapter(new EventAdapter(context, null, null, null, null, null, junes, null, null, null, null, null, null));
        } else if (position == 6) {
            holder.monthTv.setText("July");
            if (julys > 0) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.noEventFound.setVisibility(View.GONE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 7, eventsMixList));

//                holder.recyclerView.setAdapter(new EventAdapter(context,));
            } else {
                holder.recyclerView.setVisibility(View.GONE);
                holder.noEventFound.setVisibility(View.VISIBLE);
            }
//            if (!(julies.size() > 0)) {
//                holder.recyclerView.setVisibility(View.GONE);
//                holder.noEventFound.setVisibility(View.VISIBLE);
//            } else {
//                holder.noEventFound.setVisibility(View.GONE);
//                holder.recyclerView.setVisibility(View.VISIBLE);
//            }
//            holder.recyclerView.setAdapter(new EventAdapter(context, null, null, null, null, null, null, julies, null, null, null, null, null));
        } else if (position == 7) {
            holder.monthTv.setText("August");
            if (augs > 0) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.noEventFound.setVisibility(View.GONE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 8, eventsMixList));

//                holder.recyclerView.setAdapter(new EventAdapter(context,));
            } else {
                holder.recyclerView.setVisibility(View.GONE);
                holder.noEventFound.setVisibility(View.VISIBLE);
            }
//            if (!(augusts.size() > 0)) {
//                holder.recyclerView.setVisibility(View.GONE);
//                holder.noEventFound.setVisibility(View.VISIBLE);
//            } else {
//                holder.noEventFound.setVisibility(View.GONE);
//                holder.recyclerView.setVisibility(View.VISIBLE);
//            }
//            holder.recyclerView.setAdapter(new EventAdapter(context, null, null, null, null, null, null, null, augusts, null, null, null, null));
        } else if (position == 8) {
            holder.monthTv.setText("September");
            if (septs > 0) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.noEventFound.setVisibility(View.GONE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 9, eventsMixList));

//                holder.recyclerView.setAdapter(new EventAdapter(context,));
            } else {
                holder.recyclerView.setVisibility(View.GONE);
                holder.noEventFound.setVisibility(View.VISIBLE);
            }
//            if (!(septembers.size() > 0)) {
//                holder.recyclerView.setVisibility(View.GONE);
//                holder.noEventFound.setVisibility(View.VISIBLE);
//            } else {
//                holder.noEventFound.setVisibility(View.GONE);
//                holder.recyclerView.setVisibility(View.VISIBLE);
//            }
//            holder.recyclerView.setAdapter(new EventAdapter(context, null, null, null, null, null, null, null, null, septembers, null, null, null));
        } else if (position == 9) {
            holder.monthTv.setText("October");
            if (octs > 0) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.noEventFound.setVisibility(View.GONE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 10, eventsMixList));
//                holder.recyclerView.setAdapter(new EventAdapter(context,));
            } else {
                holder.recyclerView.setVisibility(View.GONE);
                holder.noEventFound.setVisibility(View.VISIBLE);
            }
//            if (!(octobers.size() > 0)) {
//                holder.recyclerView.setVisibility(View.GONE);
//                holder.noEventFound.setVisibility(View.VISIBLE);
//            } else {
//                holder.noEventFound.setVisibility(View.GONE);
//                holder.recyclerView.setVisibility(View.VISIBLE);
//            }
//            holder.recyclerView.setAdapter(new EventAdapter(context, null, null, null, null, null, null, null, null, null, octobers, null, null));
        } else if (position == 10) {
            holder.monthTv.setText("November");
            if (novs > 0) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.noEventFound.setVisibility(View.GONE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 11, eventsMixList));

//                holder.recyclerView.setAdapter(new EventAdapter(context,));
            } else {
                holder.recyclerView.setVisibility(View.GONE);
                holder.noEventFound.setVisibility(View.VISIBLE);
            }
//            if (!(novembers.size() > 0)) {
//                holder.recyclerView.setVisibility(View.GONE);
//                holder.noEventFound.setVisibility(View.VISIBLE);
//            } else {
//                holder.noEventFound.setVisibility(View.GONE);
//                holder.recyclerView.setVisibility(View.VISIBLE);
//            }
//            holder.recyclerView.setAdapter(new EventAdapter(context, null, null, null, null, null, null, null, null, null, null, novembers, null));
        } else if (position == 11) {
            holder.monthTv.setText("December");
            if (decs > 0) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.noEventFound.setVisibility(View.GONE);
                holder.recyclerView.setAdapter(new EventAdapter(context, 12, eventsMixList));

//                holder.recyclerView.setAdapter(new EventAdapter(context,));
            } else {
                holder.recyclerView.setVisibility(View.GONE);
                holder.noEventFound.setVisibility(View.VISIBLE);
            }
//            if (!(decembers.size() > 0)) {
//                holder.recyclerView.setVisibility(View.GONE);
//                holder.noEventFound.setVisibility(View.VISIBLE);
//            } else {
//                holder.noEventFound.setVisibility(View.GONE);
//                holder.recyclerView.setVisibility(View.VISIBLE);
//            }
//            holder.recyclerView.setAdapter(new EventAdapter(context, null, null, null, null, null, null, null, null, null, null, null, decembers));
        }

    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public void filterList(ArrayList<EventsMix> filteredlist) {
        eventsMixList = filteredlist;
        notifyDataSetChanged();
    }

//    public void filterList(List<AllEvents> filterlist) {
////        allEvents = filterlist;
//        notifyDataSetChanged();
//    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        TextView monthTv, noEventFound;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            monthTv = itemView.findViewById(R.id.monthTv);
            recyclerView = itemView.findViewById(R.id.EventRv);
            noEventFound = itemView.findViewById(R.id.noEventFound);
        }
    }
}
