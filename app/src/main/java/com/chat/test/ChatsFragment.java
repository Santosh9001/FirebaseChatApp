package com.chat.test;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatsFragment extends Fragment {


    public ChatsFragment() {
        // Required empty public constructor
    }


    private Chats chats;

    private static final int SIGN_IN_REQ_CODE = 1;

    private FirebaseListAdapter<Chats> chatsAdapter;


    private RelativeLayout parentLayout;
    private FloatingActionButton fab;
    private EditText msg;
    private ListView messages;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parentLayout = view.findViewById(R.id.parentLayout);
        fab = view.findViewById(R.id.fab);
        msg = view.findViewById(R.id.msg);
        messages = view.findViewById(R.id.messageList);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = msg.getText().toString();
                FirebaseDatabase.
                        getInstance().
                        getReference().
                        push().
                        setValue(new Chats(message, FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                msg.setText("");
            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_REQ_CODE);
        } else {
            Snackbar.make(parentLayout, "Welcome " + FirebaseAuth.getInstance().getCurrentUser().getEmail(), Snackbar.LENGTH_SHORT).show();
            displayChatMessages();
        }
    }

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    ChatListViewModel chatListViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ChatApplication) getActivity().getApplication()).
                getApplicationComponent().
                inject(this);
    }

    private void displayChatMessages() {
        if (chatsAdapter != null) {
            chatsAdapter.startListening();
        }

        Query query = FirebaseDatabase.getInstance().getReference();
        FirebaseListOptions<Chats> options = new FirebaseListOptions.Builder<Chats>()
                .setQuery(query, Chats.class)
                .setLayout(R.layout.message_lists)
                .build();

        chatsAdapter = new FirebaseListAdapter<Chats>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Chats model, int position) {
                TextView user = v.findViewById(R.id.user);
                TextView time = v.findViewById(R.id.time);
                TextView msg = v.findViewById(R.id.msg);

                user.setText(model.getUser());
                time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getTime()));
                msg.setText(model.getMessage());

                // insert chats into the Chats database
                chatListViewModel.insertChats(model);
            }
        };

        messages.setAdapter(chatsAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (chatsAdapter != null) {
            chatsAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        chatsAdapter.stopListening();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_REQ_CODE) {

            if (resultCode == RESULT_OK) {

                Snackbar.make(parentLayout, "Sign In Success", Snackbar.LENGTH_SHORT).show();
                displayChatMessages();
            } else {

                Snackbar.make(parentLayout, "Sign In Failure", Snackbar.LENGTH_SHORT).show();
                getActivity().finish();
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        chatListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ChatListViewModel.class);

        chatListViewModel.getChatsLists().observe(this, new Observer<List<Chats>>() {
            @Override
            public void onChanged(List<Chats> chats) {
                if (chats.size() > 0)
                    Log.e("Chats", chats.get(0).getMessage());
            }
        });
    }
}
