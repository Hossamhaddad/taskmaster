package com.example.taskmaster;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task")
public class Task {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public long id;
    private String title;
    private String body;
    private String state;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Task(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
    }

}
