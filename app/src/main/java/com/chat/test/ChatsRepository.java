package com.chat.test;

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
