package com.chat.test;


import android.app.Application;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private final ChatsDatabse chatsDatabse;


    public RoomModule(Application application) {
        this.chatsDatabse = Room.databaseBuilder(
                application,
                ChatsDatabse.class,
                "Chats.db").
                build();
    }

    @Provides
    @Singleton
    ChatsRepository provideChatsListRepository(ChatsDao chatsDao){
        return new ChatsRepository(chatsDao);
    }

    @Provides
    @Singleton
    ChatsDao provideChatsItemDao(ChatsDatabse database){
        return database.chatsDao();
    }

    @Provides
    @Singleton
    ChatsDatabse provideChatsDatabase(Application application){
        return chatsDatabse;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(ChatsRepository repository){
        return new CustomViewModelFactory(repository);
    }
}
