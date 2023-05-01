package com.example.staffinemployees.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.EventsByYearResponse;
import com.example.staffinemployees.Response.EventsMix;
import com.example.staffinemployees.Response.LoginResponse;
import com.example.staffinemployees.Retrofit.RetrofitServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Path;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    Context context;
    int month;
    ApiInterface apiInterface;
    List<EventsMix> eventsMixList;
    List<EventsMix> currentMonthEventsList;
    String image, image1, image2, image3, title, desc, date, location;
    ProgressDialog progress;
    SharedPreferences sharedPreferences;
    ArrayList<String> interestedMembers;

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
        progress = new ProgressDialog(context);
        progress.setCancelable(false);
        interestedMembers = new ArrayList<>();
        sharedPreferences = context.getSharedPreferences("staffin", Context.MODE_PRIVATE);
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
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
        List<String> membersArray = List.of(singleUnit.getAddMember().split(","));
        holder.interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<LoginResponse> callPostUpdateInterested;
                String interestedEmployees = singleUnit.getAdd_intruted_member();
                if (interestedEmployees == null) {
                    interestedEmployees = sharedPreferences.getString("name", "0");
                    callPostUpdateInterested = apiInterface.postUpdateInterested(singleUnit.getId(), interestedEmployees);

                } else {
                    interestedEmployees += ",,,,,,,,," + sharedPreferences.getString("name", "0");
                    callPostUpdateInterested = apiInterface.postUpdateInterested(singleUnit.getId(), interestedEmployees);

                }
                progress.show();
                callPostUpdateInterested.enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        progress.dismiss();
                        if (response.isSuccessful()) {
                            Toast.makeText(context, "You are interested in this event", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "some error occured", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(context, "some failure occured", Toast.LENGTH_SHORT).show();
                        progress.dismiss();

                        Log.d("ndf", t.getMessage());
                    }
                });

            }
        });

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

                interestedMembers.clear();

                if (singleUnit.getAdd_intruted_member() != null) {
                    Log.d("fsdfsdfasd", singleUnit.getAdd_intruted_member());

                    if (singleUnit.getAdd_intruted_member().contains(",,,,,,,,,")) {
                        String[] names = new String[singleUnit.getAdd_intruted_member().split(",,,,,,,,,").length];
                        names = singleUnit.getAdd_intruted_member().split(",,,,,,,,,");

                        for (String name : names) {
                            if (!Objects.equals(name, "0")) {
                                interestedMembers.add(name);
                            }
                        }

                        intent.putStringArrayListExtra("interested", interestedMembers);
                    } else {
                        interestedMembers.add(singleUnit.getAdd_intruted_member());
                        intent.putStringArrayListExtra("interested", interestedMembers);
                    }
                }


                intent.putExtra("image", image);
                intent.putExtra("image1", image1);
                intent.putExtra("image2", image2);
                intent.putExtra("image3", image3);
                intent.putExtra("title", title);
                intent.putExtra("desc", desc);
                intent.putExtra("date", date);
                intent.putExtra("location", location);


                intent.putStringArrayListExtra("members", new ArrayList<>(membersArray));


                context.startActivity(intent);
//                context.startActivity(new Intent(context, InsideEvent.class));
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
            interested = itemView.findViewById(R.id.interested);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtEventName = itemView.findViewById(R.id.txtEventName);
            el1 = itemView.findViewById(R.id.el1);
            el2 = itemView.findViewById(R.id.el2);
            el3 = itemView.findViewById(R.id.el3);
        }
    }
}
