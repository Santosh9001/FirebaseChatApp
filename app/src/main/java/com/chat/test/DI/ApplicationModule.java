package com.chat.test.DI;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final ChatApplication application;

    public ApplicationModule(ChatApplication application) {
        this.application = application;
    }

    @Provides
    ChatApplication provideChatApplication(){
        return application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }
}
