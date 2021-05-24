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
        TextView taskDet = findViewById(R.id.taskDetail);
       String task=getIntent().getExtras().getString("task");
        taskDet.setText(task);
    }
}