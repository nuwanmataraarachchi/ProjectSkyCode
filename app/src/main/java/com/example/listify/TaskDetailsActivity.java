package com.example.listify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listify.database.DBHelperTasks;

public class TaskDetailsActivity extends AppCompatActivity {

    EditText add_title, add_date, add_startTime, add_endTime;
    Button btn_update;

    String id, title, date, start_time, end_time, duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.task_details);

        add_title = findViewById(R.id.update_title);
        add_date = findViewById(R.id.update_date);
        add_startTime = findViewById(R.id.update_start_time);
        add_endTime = findViewById(R.id.update_end_time);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getAndSetIntentData();
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title")){
            //getting date
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");

            //setting date
            add_title.setText(title);

        }else Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
    }

    public void update(View view){
        DBHelperTasks dbHelper = new DBHelperTasks(TaskDetailsActivity.this);
        dbHelper.updateTask(id,title,date,start_time,end_time,duration);
    }

    public void delete(View view){
        DBHelperTasks dbHelper = new DBHelperTasks(TaskDetailsActivity.this);
        dbHelper.deleteTask(id);
    }
}