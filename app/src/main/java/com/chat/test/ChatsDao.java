package com.chat.test;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ChatsDao {


    @Query("select * FROM Chats")
    LiveData<List<Chats>> getChatLists();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertChatItems(Chats chats);

}
