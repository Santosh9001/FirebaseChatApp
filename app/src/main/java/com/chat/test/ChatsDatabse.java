package com.chat.test;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Chats.class,version = 1)
public abstract class ChatsDatabse extends RoomDatabase {

    public abstract ChatsDao chatsDao();
}
