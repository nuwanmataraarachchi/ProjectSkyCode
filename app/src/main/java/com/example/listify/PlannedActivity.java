package com.example.listify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.listify.database.DBHelperTasks;

import java.util.ArrayList;

public class PlannedActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelperTasks myDB;
    ArrayList<String> task_id, title, date, startTime, endTime;
    CustomAdapterTask customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planned);
        recyclerView = findViewById(R.id.recycler);

        myDB = new DBHelperTasks(PlannedActivity.this);
        task_id = new ArrayList<>();
        title = new ArrayList<>();
        date = new ArrayList<>();
        startTime = new ArrayList<>();
        endTime = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapterTask(PlannedActivity.this, task_id, title, date, startTime, endTime);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(PlannedActivity.this));

    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                task_id.add(cursor.getString(0));
                title.add(cursor.getString(1));
                date.add(cursor.getString(2));
                startTime.add(cursor.getString(3));
                endTime.add(cursor.getString(4));
            }
        }
    }

}