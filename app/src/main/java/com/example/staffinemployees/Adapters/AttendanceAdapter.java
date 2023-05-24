package com.example.staffinemployees.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.AttendanceResponse;
import com.example.staffinemployees.Response.GetMonthlyAttendance;
import com.example.staffinemployees.Response.PresentAbsentMix;

import java.util.Arrays;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    List<PresentAbsentMix> presentAbsentMixList;
    Context context;


    public AttendanceAdapter(List<PresentAbsentMix> presentAbsentMixList, Context context) {
        this.presentAbsentMixList = presentAbsentMixList;
        this.context = context;
    }

    @NonNull
    @Override
    public AttendanceAdapter.AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_month_attendance_layout, parent, false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceAdapter.AttendanceViewHolder holder, int position) {

        PresentAbsentMix singleUnit = presentAbsentMixList.get(position);
        String punchIn = ".....";
        String punchOut = ".....";

        // tareekh
        if (singleUnit.getDate().contains("T")) {
            holder.dateTv.setText(singleUnit.getDate().split("T")[0].split("-")[2]);
            holder.dayTv.setText(idharSeAlooDaaloUdharSeSonaNikaalo(Integer.parseInt(singleUnit.getDate().split("T")[0].split("-")[1])));

        } else {
            holder.dateTv.setText(singleUnit.getDate().split("-")[2]);
            holder.dayTv.setText(idharSeAlooDaaloUdharSeSonaNikaalo(Integer.parseInt(singleUnit.getDate().split("-")[1])));

        }

        // month

        if (singleUnit.getStatus().equalsIgnoreCase("present")) {
            holder.status.setText(singleUnit.getStatus());
            holder.status.setBackgroundResource(R.drawable.bg_green);
            punchIn = singleUnit.getTime_in();
            holder.inTimeTv.setText(punchIn);
            punchOut = singleUnit.getTime_out();
            if (singleUnit.getTime_out() != null) {
                Log.d("dfnsdfsdf", punchOut);
                holder.outTimeTv.setText(punchOut);
            } else {
//                Toast.makeText(context, "...", Toast.LENGTH_SHORT).show();
            }

        } else if (singleUnit.getStatus().equalsIgnoreCase("absent")) {
            holder.status.setText(singleUnit.getStatus());
            holder.status.setBackgroundResource(R.drawable.bg_red);
            holder.inTimeTv.setText("no data");
            holder.outTimeTv.setText("no data");
        } else if (singleUnit.getStatus().equalsIgnoreCase("halfDay")) {
            holder.status.setText("half Day");
            holder.status.setBackgroundResource(R.drawable.bg_orange);
            punchIn = singleUnit.getTime_in();
            holder.inTimeTv.setText(punchIn);
            punchOut = singleUnit.getTime_out();
            holder.outTimeTv.setText(punchOut);
        } else if (singleUnit.getStatus().equalsIgnoreCase("late")) {
            holder.status.setText(singleUnit.getStatus());
            holder.status.setBackgroundResource(R.drawable.bg_blue);
            punchIn = singleUnit.getTime_in();
            holder.inTimeTv.setText(punchIn);
            punchOut = singleUnit.getTime_out();
            holder.outTimeTv.setText(punchOut);
        } else if (singleUnit.getStatus().equalsIgnoreCase("paidLeave")) {
            holder.status.setText("paid Leave");
            holder.status.setBackgroundResource(R.drawable.bg_purple);
            holder.inTimeTv.setText("no data");
            holder.outTimeTv.setText("no data");
        } else if (singleUnit.getStatus().equalsIgnoreCase("doublePresent")) {
            holder.status.setText("double present");
            holder.status.setBackgroundResource(R.drawable.bg_green);
            holder.inTimeTv.setText("no data");
            holder.outTimeTv.setText("no data");
        }
        // time in and time out needed


//        holder.dayTv.setText(singleUnit.getDay());
//        holder.dateTv.setText(singleUnit.getDate());
//        holder.inTimeTv.setText(singleUnit.getInTime());
//        holder.outTimeTv.setText(singleUnit.getOutTime());
//        holder.status.setText(singleUnit.getStatus());
    }

    @Override
    public int getItemCount() {
        return presentAbsentMixList.size();
    }

    public class AttendanceViewHolder extends RecyclerView.ViewHolder {
        TextView dateTv, dayTv, inTimeTv, outTimeTv;
        AppCompatTextView status;

        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTv = itemView.findViewById(R.id.dateTv);
            dayTv = itemView.findViewById(R.id.dayTv);
            inTimeTv = itemView.findViewById(R.id.inTimeTv);
            outTimeTv = itemView.findViewById(R.id.outTimeTv);
            status = itemView.findViewById(R.id.status);
        }
    }

    private String idharSeAlooDaaloUdharSeSonaNikaalo(int aaloo) {
        switch (aaloo) {
            case 1:
                return "jan";

            case 2:
                return "feb";

            case 3:
                return "mar";

            case 4:
                return "apr";
            case 5:
                return "may";

            case 6:
                return "jun";
            case 7:

                return "jul";
            case 8:

                return "aug";
            case 9:

                return "sep";

            case 10:

                return "oct";

            case 11:

                return "nov";

            case 12:

                return "dec";
            default:
                return "";
        }
    }

}
