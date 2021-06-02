package com.example.taskmaster;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskListner {
    TextView userName;
    List<Task> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
           userName=findViewById(R.id.viewUserName);

//        findViewById(R.id.SignUpPage).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent signUpPage=new Intent(MainActivity.this,SignUp.class);
//                startActivity(signUpPage);
//            }
//        });

        Intent mainAct=new Intent(this,MainActivity.class);

//        try {
//            Amplify.addPlugin(new AWSDataStorePlugin());
//            Amplify.addPlugin(new AWSApiPlugin());
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.configure(getApplicationContext());
//
//            Log.i("MyAmplifyApp", "Initialized Amplify.");
//        }catch (AmplifyException exception){
//         Log.i("Error" ,exception.toString());
//        };
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


//        Button task1=findViewById(R.id.task1);
//        task1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent task1=new Intent(MainActivity.this,TaskDetail.class);
//                task1.putExtra("task","task1");
//                startActivity(task1);
//            }
//        });
//
//        Button task2=findViewById(R.id.task2);
//        task2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent task2=new Intent(MainActivity.this,TaskDetail.class);
//                task2.putExtra("task","task2");
//                startActivity(task2);
//            }
//        });
//        Button task3=findViewById(R.id.task3);
//        task3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent task3=new Intent(MainActivity.this,TaskDetail.class);
//                task3.putExtra("task","task3");
//                startActivity(task3);
//            }
//        });

        RecyclerView recyclerView ;
      tasks=new ArrayList<>();
//        Task firstTask=new Task("firsttask","the first task body ","new");
//        Task secondTask=new Task("seondttask","the second task body ","new");
//        Task thirdTask=new Task("thirdtask","the third task body ","new");
//
//        tasks.add(firstTask);
//        tasks.add(secondTask);
//        tasks.add(thirdTask);
        TaskDatabase db = Room.databaseBuilder(getApplicationContext(),
                TaskDatabase.class, "task").allowMainThreadQueries().build();
        TaskDao taskDao=db.taskDao();
        tasks=taskDao.getAll();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        TaskAdapter adapter = new TaskAdapter(tasks,this);
        LinearLayoutManager linear=  new LinearLayoutManager(this);
        linear.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linear);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.SignOut).setOnClickListener(view->{
            Amplify.Auth.signOut(
                    () -> Log.i("AuthQuickstart", "Signed out successfully"),
                    error -> Log.e("AuthQuickstart", error.toString())
            );
            Intent backToMain=new Intent(this,SignUp.class);
            startActivity(backToMain);
        });
        if(AWSMobileClient.getInstance().getUsername()!=null){
            userName.setText(AWSMobileClient.getInstance().getUsername());
        }

    }

    @Override
    public void onTaskListner(int position) {
        Intent intent=new Intent(this,TaskDetail.class);
        intent.putExtra("title",tasks.get(position).getTitle());
        intent.putExtra("body",tasks.get(position).getBody());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userName.setText(sharedPreferences.getString("name","userName")+"'s Tasks");
        if(AWSMobileClient.getInstance().getUsername()!=null){
            userName.setText(AWSMobileClient.getInstance().getUsername());
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE) {
//            Amplify.Auth.handleWebUISignInResponse(data);
//        }
//    }
}