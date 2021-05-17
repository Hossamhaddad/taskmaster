package com.example.taskmaster;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button addNewTask =findViewById(R.id.button2);
        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast buttonToast=Toast.makeText(AddTask.this,"submitted!",Toast.LENGTH_SHORT);
                buttonToast.show();
            }
        });
    }
}