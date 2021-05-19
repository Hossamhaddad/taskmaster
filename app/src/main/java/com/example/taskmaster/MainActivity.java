package com.example.taskmaster;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskListner {
    List<Task> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTaskButton =findViewById(R.id.button);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTaskPage=new Intent(MainActivity.this,AddTask.class);
                startActivity(addTaskPage);
            }
        });

        Button showAllTasks= findViewById(R.id.button3);
        showAllTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showAllTasks=new Intent(MainActivity.this,AllTasks.class);
                startActivity(showAllTasks);
            }
        });
        Button userSettings=findViewById(R.id.settings);
        userSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userSettings=new Intent(MainActivity.this,SettingsPage.class);
                startActivity(userSettings);
            }
        });
        RecyclerView recyclerView ;
      tasks=new ArrayList<>();
        Task firstTask=new Task("firsttask","the first task body ","new");
        Task secondTask=new Task("seondttask","the second task body ","new");
        Task thirdTask=new Task("thirdtask","the third task body ","new");

        tasks.add(firstTask);
        tasks.add(secondTask);
        tasks.add(thirdTask);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        TaskAdapter adapter = new TaskAdapter(tasks);
        LinearLayoutManager linear=  new LinearLayoutManager(this);
        linear.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linear);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onTaskListner(int position) {
        Intent intent=new Intent(MainActivity.this,TaskDetail.class);
        intent.putExtra("title",tasks.get(position).getTitle());
        intent.putExtra("title",tasks.get(position).getBody());
        startActivity(intent);
    }
}