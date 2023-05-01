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

public class HomeEventsAdapter extends RecyclerView.Adapter<HomeEventsAdapter.HomeEventsViewHolder> {
    Context context;
    List<EventsMix> eventsMixList;
    List<EventsMix> currentMonthEventList;
    int month;
    ApiInterface apiInterface;
    ProgressDialog progress;
    String image, image1, image2, image3, title, desc, date, location;
    List<String> add_members;
    ArrayList<String> interestedMembers;
    String[] membersArray;
    SharedPreferences sharedPreferences;
    String[] FirstImage;
    int count;
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
        interestedMembers = new ArrayList<>();
        add_members = new ArrayList<>();
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        progress = new ProgressDialog(context);
        progress.setCancelable(false);
        sharedPreferences = context.getSharedPreferences("staffin", Context.MODE_PRIVATE);
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
        Glide.with(context.getApplicationContext()).load(singleUnit.getImage()).placeholder(R.drawable.img_birthday).into(holder.imageView);
        FirstImage = (singleUnit.getAdd_member_images().split(",,,,,,,,,,"));
        Log.e("onBindViewHolder: ", "Images");
        if (Integer.parseInt(singleUnit.getAdd_member_count()) == 1) {
            Log.e("onBindViewHolder: ", "FirstImages");
            String image1 = FirstImage[0];
            holder.el1.setVisibility(View.VISIBLE);
            holder.el2.setVisibility(View.INVISIBLE);
            holder.el3.setVisibility(View.INVISIBLE);
            holder.el4.setVisibility(View.INVISIBLE);
            Glide.with(context.getApplicationContext()).load(image1).placeholder(R.drawable.img_user).into(holder.el1);
        } else if (Integer.parseInt(singleUnit.getAdd_member_count()) == 2) {
            Log.e("onBindViewHolder: ", "SecondImages");
            String image1 = FirstImage[0];
            String image2 = FirstImage[1];
            holder.el1.setVisibility(View.VISIBLE);
            holder.el2.setVisibility(View.VISIBLE);
            holder.el3.setVisibility(View.INVISIBLE);
            holder.el4.setVisibility(View.INVISIBLE);
            Glide.with(context.getApplicationContext()).load(image1).placeholder(R.drawable.img_user).into(holder.el1);
            Glide.with(context.getApplicationContext()).load(image2).placeholder(R.drawable.img_user).into(holder.el2);
        } else if (Integer.parseInt(singleUnit.getAdd_member_count()) == 3) {
            Log.e("onBindViewHolder: ", "ThirdImages");
            String image1 = FirstImage[0];
            String image2 = FirstImage[1];
            String image3 = FirstImage[2];
            holder.el1.setVisibility(View.VISIBLE);
            holder.el2.setVisibility(View.VISIBLE);
            holder.el3.setVisibility(View.VISIBLE);
            holder.el4.setVisibility(View.INVISIBLE);
            Glide.with(context.getApplicationContext()).load(image1).placeholder(R.drawable.img_user).into(holder.el1);
            Glide.with(context.getApplicationContext()).load(image2).placeholder(R.drawable.img_user).into(holder.el2);
            Glide.with(context.getApplicationContext()).load(image3).placeholder(R.drawable.img_user).into(holder.el3);
        } else if (Integer.parseInt(singleUnit.getAdd_member_count()) >= 4) {
            String image1 = FirstImage[0];
            String image2 = FirstImage[1];
            String image3 = FirstImage[2];
            holder.el1.setVisibility(View.VISIBLE);
            holder.el2.setVisibility(View.VISIBLE);
            holder.el3.setVisibility(View.VISIBLE);
            Log.e("onBindViewHolder: ", "CountImages");
            Glide.with(context.getApplicationContext()).load(image1).placeholder(R.drawable.img_user).into(holder.el1);
            Glide.with(context.getApplicationContext()).load(image2).placeholder(R.drawable.img_user).into(holder.el2);
            Glide.with(context.getApplicationContext()).load(image3).placeholder(R.drawable.img_user).into(holder.el3);
            count = Integer.parseInt(singleUnit.getAdd_member_count());
            count -= 3;
            if (count == 0) {
                holder.el4.setVisibility(View.INVISIBLE);
            } else {
                holder.el4.setVisibility(View.VISIBLE);
                holder.el4.setText(count + "+");
            }
        } else {
            Log.e("onBindViewHolder: ", "else me aagya");
            holder.el1.setVisibility(View.INVISIBLE);
            holder.el2.setVisibility(View.INVISIBLE);
            holder.el3.setVisibility(View.INVISIBLE);
            holder.el4.setVisibility(View.INVISIBLE);
        }
        holder.textView5.setText(singleUnit.getLocation());
        membersArray = new String[singleUnit.getAddMember().split(",").length];
        List<String> membersArray = List.of(singleUnit.getAddMember().split(","));


        holder.interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String interestedEmployees = singleUnit.getAdd_intruted_member();
                if (interestedEmployees == null) {
                    interestedEmployees = sharedPreferences.getString("name", "0");
                } else {
                    interestedEmployees += ",,,,,,,,," + sharedPreferences.getString("name", "0");
                }

                Call<LoginResponse> callPostUpdateInterested = apiInterface.postUpdateInterested(singleUnit.getId(), interestedEmployees);
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
        return currentMonthEventList.size();
    }

    public class HomeEventsViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout card;
        TextView txtDate, txtEventName, textView5, interested, el4;
        ImageView imageView, el1, el2, el3;

        public HomeEventsViewHolder(@NonNull View itemView) {
            super(itemView);
            el4 = itemView.findViewById(R.id.el4);
            el1 = itemView.findViewById(R.id.el1);
            el2 = itemView.findViewById(R.id.el2);
            el3 = itemView.findViewById(R.id.el3);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtEventName = itemView.findViewById(R.id.txtEventName);
            textView5 = itemView.findViewById(R.id.textView5);
            card = itemView.findViewById(R.id.cardEvent);
            imageView = itemView.findViewById(R.id.imageView);
            interested = itemView.findViewById(R.id.interested);
        }
    }
}
