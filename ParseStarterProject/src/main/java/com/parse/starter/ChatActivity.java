package com.parse.starter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ChatActivity extends AppCompatActivity {

    String activeUser = "";
    ArrayList<String> chat = new ArrayList<>();
    ArrayAdapter arrayAdapter;


    public void sendMessage(View view){

        final EditText chatEditText = (EditText) findViewById(R.id.chatEditText);

        ParseObject message = new ParseObject("Message");

        message.put("Sender", ParseUser.getCurrentUser().getUsername());

        message.put("Recepient", activeUser);
        Log.i("activeUser",activeUser);

        message.put("Message", chatEditText.getText().toString());

        message.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                   // Toast.makeText(ChatActivity.this,"Message Sent", Toast.LENGTH_SHORT ).show();

                    chat.add(chatEditText.getText().toString());

                    arrayAdapter.notifyDataSetChanged();

                    chatEditText.setText("");
                }
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();

        activeUser = intent.getStringExtra("username");

        Log.i("username",activeUser);
        Log.i("current user",ParseUser.getCurrentUser().getUsername());

        setTitle("Chat "+ activeUser);

        ListView chatListView = (ListView) findViewById(R.id.chatListView);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, chat);

        chatListView.setAdapter(arrayAdapter);

        ParseQuery<ParseObject> query1 = new ParseQuery<ParseObject>("Message");

        query1.whereEqualTo("Sender",ParseUser.getCurrentUser().getUsername());

        query1.whereEqualTo("Recepient",activeUser);

        ParseQuery<ParseObject> query2 = new ParseQuery<ParseObject>("Message");

        query2.whereEqualTo("Recepient",ParseUser.getCurrentUser().getUsername());

        query2.whereEqualTo("Sender",activeUser);

        List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();

        queries.add(query1);
        queries.add(query2);

        ParseQuery<ParseObject> query = ParseQuery.or(queries);

        query.orderByAscending("CreatedAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    if(objects.size()>0){

                        chat.clear();

                        for(ParseObject  message : objects){

                            String messageContent = message.getString("Message");

                            if(!message.getString("Sender").equals(ParseUser.getCurrentUser().getUsername())){

                                messageContent = ">" + messageContent;
                            }
                            chat.add(messageContent);
                            Log.i("message content", messageContent);
                        }

                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


    }



}
