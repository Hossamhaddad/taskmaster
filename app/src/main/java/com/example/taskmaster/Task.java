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
    private String key;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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
