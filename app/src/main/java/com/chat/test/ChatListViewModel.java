package com.chat.test;

import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ChatListViewModel extends ViewModel {

    private ChatsRepository repository;

    public ChatListViewModel(ChatsRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Chats>>  getChatsLists(){
        return repository.retrieveChats();
    }

    public void insertChats(Chats chats){
        InsertChats insertChats = new InsertChats();
        insertChats.execute(chats);
    }


    private class InsertChats extends AsyncTask<Chats, Void, Void>{

        @Override
        protected Void doInBackground(Chats... chats) {
            repository.insertChats(chats[0]);
            return null;
        }
    }
}
