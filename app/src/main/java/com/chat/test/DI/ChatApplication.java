package com.chat.test.DI;

import android.app.Activity;
import android.app.Application;


import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class ChatApplication extends Application implements HasActivityInjector {

    private ApplicationComponent applicationComponent;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

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

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return null;
    }
}
