package com.chat.test;


import android.app.Application;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private final ChatsDatabase chatsDatabase;


    public RoomModule(Application application) {
        this.chatsDatabase = Room.databaseBuilder(
                application,
                ChatsDatabase.class,
                "Chats.db"
        ).build();
    }

    @Provides
    @Singleton
    ChatsRepository provideChatsListRepository(ChatsDao chatsDao){
        return new ChatsRepository(chatsDao);
    }

    @Provides
    @Singleton
    ChatsDao provideChatsItemDao(ChatsDatabase database){
        return database.chatsDao();
    }

    @Provides
    @Singleton
    ChatsDatabase provideChatsDatabase(Application application){
        return chatsDatabase;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(ChatsRepository repository){
        return new CustomViewModelFactory(repository);
    }
}
