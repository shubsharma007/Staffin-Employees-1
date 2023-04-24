package com.example.staffinemployees.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.staffinemployees.InsideEvent;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.EventsByYearResponse;
import com.example.staffinemployees.Response.EventsMix;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    Context context;
    int month;
    List<EventsMix> eventsMixList;
    List<EventsMix> currentMonthEventsList;
    String image, image1, image2, image3, title, desc, date, location;
    String[] membersArray;

    public EventAdapter(Context context, int month, List<EventsMix> eventsMixList) {
        this.context = context;
        this.month = month;
        this.eventsMixList = eventsMixList;
        currentMonthEventsList = new ArrayList<>();
        for (EventsMix e : eventsMixList) {
            if (e.getMonth() == month) {
                currentMonthEventsList.add(e);
            }
        }
    }


    @NonNull
    @Override
    public EventAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_event_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.MyViewHolder holder, int position) {

        EventsMix singleUnit = currentMonthEventsList.get(position);
        holder.txtDate.setText(singleUnit.getDate());
        holder.txtEventName.setText(singleUnit.getTitleName());
        Glide.with(context.getApplicationContext()).load(singleUnit.getImage()).placeholder(R.drawable.img_birthday).into(holder.imageView);

//        holder.interested.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "You are interested in this event", Toast.LENGTH_SHORT).show();
//            }
//        });
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                membersArray = new String[singleUnit.getAddMember().split(",").length];
                List<String> membersArray = List.of(singleUnit.getAddMember().split(","));

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
                intent.putStringArrayListExtra("members", new ArrayList<>(membersArray));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return currentMonthEventsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate, txtEventName, interested;
        ConstraintLayout card;
        ImageView imageView, el1, el2, el3;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.cardEvent);
            imageView = itemView.findViewById(R.id.imageView);
//            interested = itemView.findViewById(R.id.interested);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtEventName = itemView.findViewById(R.id.txtEventName);
            el1 = itemView.findViewById(R.id.el1);
            el2 = itemView.findViewById(R.id.el2);
            el3 = itemView.findViewById(R.id.el3);
        }
    }
}
