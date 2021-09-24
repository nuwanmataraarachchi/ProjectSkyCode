package com.example.listify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TaskMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_menu);
    }

    public void openAddTask(View view) {  Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

    public void openMyDay(View view) {  Intent intent = new Intent(this, MyDayActivity.class);
        startActivity(intent);
    }

    public void openImportantTasks(View view) {  Intent intent = new Intent(this, ImportantTasksActivity.class);
        startActivity(intent);
    }

    public void openPlanned(View view) {  Intent intent = new Intent(this, PlannedActivity.class);
        startActivity(intent);
    }

}