package com.example.staffinemployees.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffinemployees.InsideEvent;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.EventsByYearResponse;
import com.example.staffinemployees.Response.EventsMix;

import java.util.ArrayList;
import java.util.List;

public class HomeEventsAdapter extends RecyclerView.Adapter<HomeEventsAdapter.HomeEventsViewHolder> {
    Context context;
    List<EventsMix> eventsMixList;
    List<EventsMix> currentMonthEventList;
    int month;
    String image, image1, image2, image3, title, desc, date, location;
    List<String> add_members;
    String[] membersArray;

//    List<EventsByYearResponse.EventDetails.January> jan;
//    List<EventsByYearResponse.EventDetails.February> feb;
//    List<EventsByYearResponse.EventDetails.March> mar;
//    List<EventsByYearResponse.EventDetails.April> apr;
//    List<EventsByYearResponse.EventDetails.May> may;
//    List<EventsByYearResponse.EventDetails.June> june;
//    List<EventsByYearResponse.EventDetails.July> july;
//    List<EventsByYearResponse.EventDetails.August> aug;
//    List<EventsByYearResponse.EventDetails.September> sept;
//    List<EventsByYearResponse.EventDetails.October> oct;
//    List<EventsByYearResponse.EventDetails.November> nov;
//    List<EventsByYearResponse.EventDetails.December> dec;
//    int size = 0;

    public HomeEventsAdapter(List<EventsMix> eventsMixList, int month, Context context) {
        this.context = context;
        this.eventsMixList = eventsMixList;
        this.month = month;
        currentMonthEventList = new ArrayList<>();
        for (EventsMix e : eventsMixList) {
            if (e.getMonth() == month) {
                currentMonthEventList.add(e);
            }
        }
        add_members = new ArrayList<>();

    }

//    public HomeEventsAdapter(Context context, List<EventsByYearResponse.EventDetails.January> jan, List<EventsByYearResponse.EventDetails.February> feb, List<EventsByYearResponse.EventDetails.March> mar, List<EventsByYearResponse.EventDetails.April> apr, List<EventsByYearResponse.EventDetails.May> may, List<EventsByYearResponse.EventDetails.June> june, List<EventsByYearResponse.EventDetails.July> july, List<EventsByYearResponse.EventDetails.August> aug, List<EventsByYearResponse.EventDetails.September> sept, List<EventsByYearResponse.EventDetails.October> oct, List<EventsByYearResponse.EventDetails.November> nov, List<EventsByYearResponse.EventDetails.December> dec) {
////        jan = new ArrayList<>();
////        feb = new ArrayList<>();
////        mar = new ArrayList<>();
////        apr = new ArrayList<>();
////        may = new ArrayList<>();
////        june = new ArrayList<>();
////        july = new ArrayList<>();
////        aug = new ArrayList<>();
////        sept = new ArrayList<>();
////        oct = new ArrayList<>();
////        nov = new ArrayList<>();
////        dec = new ArrayList<>();
//
//        this.context = context;
//        this.jan = jan;
//        this.feb = feb;
//        this.mar = mar;
//        this.apr = apr;
//        this.may = may;
//        this.june = june;
//        this.july = july;
//        this.aug = aug;
//        this.sept = sept;
//        this.oct = oct;
//        this.nov = nov;
//        this.dec = dec;
//
//        if (jan != null) {
//            size = jan.size();
//        } else if (feb != null) {
//            size = feb.size();
//
//        } else if (mar != null) {
//            size = mar.size();
//
//        } else if (apr != null) {
//            size = apr.size();
//
//        } else if (may != null) {
//            size = may.size();
//
//        } else if (june != null) {
//            size = june.size();
//
//        } else if (july != null) {
//            size = july.size();
//
//        } else if (aug != null) {
//            size = aug.size();
//
//        } else if (sept != null) {
//            size = sept.size();
//
//        } else if (oct != null) {
//            size = oct.size();
//
//        } else if (nov != null) {
//            size = nov.size();
//
//        } else if (dec != null) {
//            size = dec.size();
//        }
//    }

    @NonNull
    @Override
    public HomeEventsAdapter.HomeEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_event_layout, parent, false);
        return new HomeEventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeEventsAdapter.HomeEventsViewHolder holder, int position) {
//        if (jan != null) {
//            holder.txtDate.setText(jan.get(position).getDate());
//            holder.txtEventName.setText(jan.get(position).getTitleName());
//            holder.textView5.setText(jan.get(position).getLocation());
//        } else if (feb != null) {
//            holder.txtDate.setText(feb.get(position).getDate());
//            holder.txtEventName.setText(feb.get(position).getTitleName());
//            holder.textView5.setText(feb.get(position).getLocation());
//        } else if (mar != null) {
//            holder.txtDate.setText(mar.get(position).getDate());
//            holder.txtEventName.setText(mar.get(position).getTitleName());
//            holder.textView5.setText(mar.get(position).getLocation());
//        } else if (apr != null) {
//            holder.txtDate.setText(apr.get(position).getDate());
//            holder.txtEventName.setText(apr.get(position).getTitleName());
//            holder.textView5.setText(apr.get(position).getLocation());
//        } else if (may != null) {
//            holder.txtDate.setText(may.get(position).getDate());
//            holder.txtEventName.setText(may.get(position).getTitleName());
//            holder.textView5.setText(may.get(position).getLocation());
//        } else if (june != null) {
//            holder.txtDate.setText(june.get(position).getDate());
//            holder.txtEventName.setText(june.get(position).getTitleName());
//            holder.textView5.setText(june.get(position).getLocation());
//        } else if (july != null) {
//            holder.txtDate.setText(july.get(position).getDate());
//            holder.txtEventName.setText(july.get(position).getTitleName());
//            holder.textView5.setText(july.get(position).getLocation());
//        } else if (aug != null) {
//            holder.txtDate.setText(aug.get(position).getDate());
//            holder.txtEventName.setText(aug.get(position).getTitleName());
//            holder.textView5.setText(aug.get(position).getLocation());
//        } else if (sept != null) {
//            holder.txtDate.setText(sept.get(position).getDate());
//            holder.txtEventName.setText(sept.get(position).getTitleName());
//            holder.textView5.setText(sept.get(position).getLocation());
//        } else if (oct != null) {
//            holder.txtDate.setText(oct.get(position).getDate());
//            holder.txtEventName.setText(oct.get(position).getTitleName());
//            holder.textView5.setText(oct.get(position).getLocation());
//        } else if (nov != null) {
//            holder.txtDate.setText(nov.get(position).getDate());
//            holder.txtEventName.setText(nov.get(position).getTitleName());
//            holder.textView5.setText(nov.get(position).getLocation());
//        } else if (dec != null) {
//            holder.txtDate.setText(dec.get(position).getDate());
//            holder.txtEventName.setText(dec.get(position).getTitleName());
//            holder.textView5.setText(dec.get(position).getLocation());
//        }
        EventsMix singleUnit = currentMonthEventList.get(position);
        holder.txtDate.setText(singleUnit.getDate());
        holder.txtEventName.setText(singleUnit.getTitleName());
        holder.textView5.setText(singleUnit.getLocation());
        membersArray = new String[singleUnit.getAddMember().split(",").length];
        List<String> membersArray= List.of(singleUnit.getAddMember().split(","));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InsideEvent.class);
                image = singleUnit.getImage();
                image1 = singleUnit.getImg1();
                image2 = singleUnit.getImg2();
                image3 = singleUnit.getImg3();
                title = singleUnit.getTitleName();
                desc = singleUnit.getDescription();
                date = singleUnit.getDate();
                location = singleUnit.getLocation();

                intent.putExtra("image", image);
                intent.putExtra("image1", image1);
                intent.putExtra("image2", image2);
                intent.putExtra("image3", image3);
                intent.putExtra("title", title);
                intent.putExtra("desc", desc);
                intent.putExtra("date", date);
                intent.putExtra("location", location);
                intent.putStringArrayListExtra("members",new ArrayList<>(membersArray));
                context.startActivity(intent);
//                context.startActivity(new Intent(context, InsideEvent.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return currentMonthEventList.size();
    }

    public class HomeEventsViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout card;
        TextView txtDate, txtEventName, textView5;

        public HomeEventsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtEventName = itemView.findViewById(R.id.txtEventName);
            textView5 = itemView.findViewById(R.id.textView5);
            card = itemView.findViewById(R.id.cardEvent);
        }
    }
}
