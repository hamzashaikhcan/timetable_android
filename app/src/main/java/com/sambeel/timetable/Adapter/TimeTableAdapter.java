package com.sambeel.timetable.Adapter;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sambeel.timetable.Classes.Timetable;
import com.sambeel.timetable.R;
import com.sambeel.timetable.Services.MyBroadcastReceiver;

import java.util.Calendar;
import java.util.List;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.viewHolder>{

    List<Timetable> data;
    Context mCtx;
    int requestCode = 280192;

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

        String[] separated = data.get(position).getTime().split(":");
        int hours  = Integer.valueOf(separated[0]);
        int minutes = Integer.valueOf(separated[1]);

        cancelAlarm();
        setAlarm(1, hours, minutes);
    }

    public void setAlarm(int dayOfWeek, int hours, int minutes) {


        // alarm first vibrate at 14 hrs and 40 min and repeat itself at ONE_HOUR interval
        PendingIntent pendingIntent;
        AlarmManager alarmManager;
        Intent intent;
        intent = new Intent(mCtx, MyBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(
                mCtx.getApplicationContext(), requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);

        alarmManager = (AlarmManager) mCtx.getSystemService(ALARM_SERVICE);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_HOUR, pendingIntent);

//        Toast.makeText(mCtx, "Alarm will vibrate at "+hours+":"+minutes+" time specified", Toast.LENGTH_SHORT).show();
    }

    private void cancelAlarm() {

        AlarmManager alarmManager2 = (AlarmManager) mCtx.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(mCtx.getApplicationContext(),requestCode,new Intent(mCtx, MyBroadcastReceiver.class),0);
        alarmManager2.cancel(pendingIntent2);

//        Toast.makeText(mCtx.getApplicationContext(), "Alarm Cancelled - "+ requestCode, Toast.LENGTH_LONG).show();
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
