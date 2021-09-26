package com.example.listify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterTask extends RecyclerView.Adapter<CustomAdapterTask.MyViewHolder> {

    private Context context;
    private ArrayList task_id, title, date, startTime, endTime;


    CustomAdapterTask(Context context, ArrayList task_id, ArrayList title, ArrayList date, ArrayList startTime,
                      ArrayList endTime){
        this.context = context;
        this.task_id = task_id;
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_tasks, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.task_title.setText(String.valueOf(title.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TaskDetailsActivity.class);
                intent.putExtra("id", String.valueOf(task_id.get(position)));
                intent.putExtra("title", String.valueOf(title.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                intent.putExtra("startTime", String.valueOf(startTime.get(position)));
                intent.putExtra("endTime", String.valueOf(endTime.get(position)));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return task_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView task_title;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            task_title = itemView.findViewById(R.id.task_title);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
