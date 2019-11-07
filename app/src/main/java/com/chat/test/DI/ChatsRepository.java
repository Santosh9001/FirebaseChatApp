package com.chat.test.DI;

import com.chat.test.M.Chats;
import com.chat.test.M.ChatsDao;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class ChatsRepository {

    private final ChatsDao chatsDao;

    @Inject
    public ChatsRepository(ChatsDao chatsDao) {
        this.chatsDao = chatsDao;
    }

    public LiveData<List<Chats>> retrieveChats(){
        return chatsDao.getChatLists();
    }

    public void insertChats(Chats chats){
        chatsDao.insertChatItems(chats);
    }
}
