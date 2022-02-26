package com.sambeel.timetable.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sambeel.timetable.Classes.Timetable;
import com.sambeel.timetable.R;

import java.util.List;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.viewHolder>{

    List<Timetable> data;
    Context mCtx;

    public TimeTableAdapter(List<Timetable> data, Context mCtx) {
        this.data = data;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_timetable, parent, false);
        TimeTableAdapter.viewHolder myViewHolder = new TimeTableAdapter.viewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.time.setText(data.get(position).getTime());
        holder.subject.setText(data.get(position).getClass_name());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView time,subject;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            subject = itemView.findViewById(R.id.subject);
        }
    }
}
