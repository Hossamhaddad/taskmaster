package com.example.taskmaster;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TaskDatabase db = Room.databaseBuilder(getApplicationContext(),
                TaskDatabase.class, "tasks").allowMainThreadQueries().build();
        TaskDao taskDao=db.taskDao();
        List<Task> tasks=taskDao.getAll();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button addNewTask =findViewById(R.id.button2);
        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast buttonToast=Toast.makeText(AddTask.this,db.toString(),Toast.LENGTH_SHORT);
                buttonToast.show();
                String title=findViewById(R.id.newTaskTitle).toString();
                String description=findViewById(R.id.newTaskDescription).toString();

             Task task=new Task(title,description,"new");
             db.taskDao().insertAll(task);
                Log.d("databsae",db.toString());

            }
        });
    }
}