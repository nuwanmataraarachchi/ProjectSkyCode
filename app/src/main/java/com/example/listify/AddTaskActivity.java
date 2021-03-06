package com.example.listify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.listify.database.DBHelperTasks;

public class AddTaskActivity extends AppCompatActivity {

    EditText add_title,add_date,add_start_time,add_end_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        add_title = findViewById(R.id.add_title);
        add_date = findViewById(R.id.add_date);
        add_start_time = findViewById(R.id.add_start_time);
        add_end_time = findViewById(R.id.add_end_time);
    }

    public void saveTask(View view){
        String title = add_title.getText().toString();
        String date = add_date.getText().toString();
        String start_time = add_start_time.getText().toString();
        String end_time = add_end_time.getText().toString();
        String duration = null;

        //calculation
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(start_time);
            d2 = format.parse(end_time);

            long diff = d2.getTime() - d1.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;

            String hours = String.valueOf(diffHours);
            String minutes = String.valueOf(diffMinutes);

            duration = hours + " hours " + minutes + " minutes";

        } catch (Exception e) {
            e.printStackTrace();
        }

        DBHelperTasks dbHelper = new DBHelperTasks(this);

        if(title.isEmpty()){
            Toast.makeText(this, "Enter title", Toast.LENGTH_SHORT).show();
        }else{
            long inserted = dbHelper.addTask(title,date,start_time,end_time,duration);

            if(inserted>0){
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Retry", Toast.LENGTH_SHORT).show();
            }
        }
    }

}