package com.chat.test;

import android.app.Application;


public class ChatApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.
                builder().
                applicationModule(new ApplicationModule(this)).
                roomModule(new RoomModule(this)).
                build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
