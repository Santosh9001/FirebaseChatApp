package com.chat.test;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Chats")
public class Chats {

    private String message;

    private String user;

    private long time;

    @PrimaryKey(autoGenerate = true)
    private long id;


    public Chats(String message, String user) {
        this.message = message;
        this.user = user;

        this.time = new Date().getTime();
    }

    public Chats(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
