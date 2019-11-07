package com.chat.test.DI;


import android.app.Application;

import com.chat.test.V.ChatsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {

    void inject(ChatsFragment chatsFragment);

    Application application();
}
