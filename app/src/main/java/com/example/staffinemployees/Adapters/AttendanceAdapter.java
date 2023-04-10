package com.example.staffinemployees.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staffinemployees.MainActivity;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.AttendanceResponse;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    List<AttendanceResponse> attendanceResponseList;
    Context context;


    public AttendanceAdapter(List<AttendanceResponse> attendanceResponseList, Context context) {
        this.attendanceResponseList = attendanceResponseList;
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
        AttendanceResponse singleUnit = attendanceResponseList.get(position);
        holder.dayTv.setText(singleUnit.getDay());
        holder.dateTv.setText(singleUnit.getDate());
        holder.inTimeTv.setText(singleUnit.getInTime());
        holder.outTimeTv.setText(singleUnit.getOutTime());
        holder.status.setText(singleUnit.getStatus());
    }

    @Override
    public int getItemCount() {
        return attendanceResponseList.size();
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
}
