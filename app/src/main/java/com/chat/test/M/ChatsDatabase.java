package com.chat.test.M;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Chats.class},version = 1, exportSchema = false)
public abstract class ChatsDatabase extends RoomDatabase {

    public abstract ChatsDao chatsDao();
}
