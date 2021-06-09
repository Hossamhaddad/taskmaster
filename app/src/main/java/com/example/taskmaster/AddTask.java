package com.example.taskmaster;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

public class AddTask extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;
    String key="";
    String cityName="";
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TaskDatabase db = Room.databaseBuilder(getApplicationContext(),
                TaskDatabase.class, "task").allowMainThreadQueries().build();
        TaskDao taskDao = db.taskDao();
        List<Task> tasks = taskDao.getAll();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button addNewTask = findViewById(R.id.button2);
        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast buttonToast = Toast.makeText(AddTask.this, db.toString(), Toast.LENGTH_SHORT);
                buttonToast.show();
                EditText title = findViewById(R.id.newTaskTitle);
                EditText description = findViewById(R.id.newTaskDescription);

                Task task = new Task(title.getText().toString(), description.getText().toString(), "new");
                task.setKey(key);
                task.setCity(cityName);
                db.taskDao().insertAll(task);
                Log.d("databsae", db.toString());
                finish();

            }
        });
        Button addImage = findViewById(R.id.addImage);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("*/*");
                startActivityForResult(intent, 5000);
            }
        });
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            System.out.println("hiiiiiiiiiiiiiiiiiiiii");
                            Log.i("location service ", "location latitude  : " + location.getLatitude());
                            Log.i("location service ", "location longitude  : " + location.getLongitude());
                            Geocoder geocoder = new Geocoder(AddTask.this, Locale.getDefault());
                            try {
                                List<Address> address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 3);
                                cityName = address.get(0).getCountryName();
                                System.out.println(cityName);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
        Intent intent = getIntent();
        Uri newImage = intent.getData();
        if (newImage != null) {
            key="husam";
            File file = new File(getApplicationContext().getFilesDir(), "uploads");
            try {
                InputStream inputStream = getContentResolver().openInputStream(newImage);
                try {
                    OutputStream out = new FileOutputStream(file);
                    try {
                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = inputStream.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        uploadFile(file);
                    } finally {
                        out.close();
                    }
                } finally {
                    inputStream.close();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        }
        }

        @Override
        protected void onResume () {
            super.onResume();
            TextView counts = findViewById(R.id.textView6);
            TaskDatabase db = Room.databaseBuilder(getApplicationContext(),
                    TaskDatabase.class, "task").allowMainThreadQueries().build();
            TaskDao taskDao = db.taskDao();
            db.taskDao().getAll().size();
            counts.setText("Total Tasks :" + db.taskDao().getAll().size());
        }
        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 5000) {
                File file = new File(getApplicationContext().getFilesDir(), "uploads");
                if (resultCode == RESULT_OK) {
                    key = "haddad";
                }
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    try {
                        OutputStream out = new FileOutputStream(file);
                        try {
                            byte[] buf = new byte[1024];
                            int len;
                            while ((len = inputStream.read(buf)) > 0) {
                                out.write(buf, 0, len);
                            }
                            uploadFile(file);
                        } finally {
                            out.close();
                        }
                    } finally {
                        inputStream.close();
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    private void uploadFile(File file) {
        System.out.println("heloooo ");
        Amplify.Storage.uploadFile(
                key,
                file,
                result -> Log.d("new result", result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );
    }
}