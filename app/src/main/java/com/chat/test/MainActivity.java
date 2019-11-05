package com.chat.test;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import dagger.android.AndroidInjection;

public class MainActivity extends BaseActivity {

    private static final String TAG = "Chats";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);

        //AndroidInjection.inject(this);

        FragmentManager manager = getSupportFragmentManager();
        ChatsFragment fragment = (ChatsFragment) manager.findFragmentByTag(TAG);


        if(fragment == null){
            fragment = new ChatsFragment();
        }

        addFragmentToActivity(manager,fragment,R.id.fragmentLayout,TAG);

    }


}

