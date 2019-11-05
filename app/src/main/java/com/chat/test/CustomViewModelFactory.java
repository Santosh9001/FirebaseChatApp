package com.chat.test;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

class CustomViewModelFactory implements ViewModelProvider.Factory {

    private final ChatsRepository repository;

    public CustomViewModelFactory(ChatsRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(ChatListViewModel.class))
            return (T) new ChatListViewModel(repository);
        else
            throw new IllegalArgumentException("No View Model");
    }
}
