package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button button=findViewById(R.id.saveName);
        EditText userName=findViewById(R.id.addUserName);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast buttonToast=Toast.makeText(SettingsPage.this,"submitted!",Toast.LENGTH_SHORT);
                buttonToast.show();
                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor edit=sharedPreferences.edit();
                edit.putString("name",userName.getText().toString());
                edit.apply();
                finish();
            }
        });
    }
}