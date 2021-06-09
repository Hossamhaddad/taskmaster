package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView titleDetail = (TextView) findViewById(R.id.taskDetail);

        titleDetail.setText(getIntent().getExtras().getString("title"));

        TextView bodyDetail = (TextView) findViewById(R.id.taskDescription);

        bodyDetail.setText(getIntent().getExtras().getString("body"));

        TextView cityDetails=findViewById(R.id.city);
        cityDetails.setText(getIntent().getExtras().getString("city"));

//        TextView taskDet = findViewById(R.id.taskDetail);
//        String task = getIntent().getExtras().getString("task");
//        taskDet.setText(task);
    }

}