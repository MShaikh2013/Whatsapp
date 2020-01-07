/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity {

  Boolean loginMode = false;

  public void redirectIfLoggedIn() {

    if (ParseUser.getCurrentUser() != null) {

      Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
      startActivity(intent);
    }
  }

  public void toggleLoginSignup(View view){

    TextView toggleLoginSignup = (TextView) findViewById(R.id.textViewToggleLoginMode);
    Button buttonSignupLogin =(Button) findViewById(R.id.buttonSignup);

    if (loginMode==false){
      loginMode = true;
      toggleLoginSignup.setText("Or Sign Up");
      buttonSignupLogin.setText("Login");

    }else{
      loginMode = false;
      toggleLoginSignup.setText("Or Login");
      buttonSignupLogin.setText("Sign Up");
    }
  }

  public void signupLogin(View view) {

    EditText usernameEditText = (EditText) findViewById(R.id.editTextUsername);
    EditText passwordEditText = (EditText) findViewById(R.id.editTextPassword);

    if (loginMode){

      ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {

          if(e == null){
            Log.i("Login", "Successful");
            redirectIfLoggedIn();

          }else{
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
          }
        }
      });

    }else {
      ParseUser user = new ParseUser();

      user.setUsername(usernameEditText.getText().toString());
      user.setPassword(passwordEditText.getText().toString());

      user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {
          if (e == null) {
            Log.i("Signup", "Successful");
            redirectIfLoggedIn();
          } else {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
          }

        }
      });

    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    redirectIfLoggedIn();


    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }



}