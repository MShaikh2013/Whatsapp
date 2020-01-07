/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    // Setting Bitnami application password to 'ygfpdr9x3KJX'

    //bitnami@ip-172-31-24-163:~$ cd apps/parse/htdocs
    //bitnami@ip-172-31-24-163:~/apps/parse/htdocs$ vi server.js

    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("d4244dfa3aa02c5edd431ade95032976e3084613")//d4244dfa3aa02c5edd431ade95032976e3084613
            .clientKey("ec9654ae31a89a30945450b3b6e6f58438522fec")//ec9654ae31a89a30945450b3b6e6f58438522fec
            .server("http://18.224.4.26:80/parse/")
            .build()
    );

    /*appId: "d4244dfa3aa02c5edd431ade95032976e3084613",
    masterKey: "ec9654ae31a89a30945450b3b6e6f58438522fec",
    fileKey: "5843ab05cc13649216bea0074065856e7bdab6fc",
    serverURL: "http://18.224.4.26:80/parse"
*/

    /*ParseObject object = new ParseObject("ExampleObject");
    object.put("myNumber", "123");
    object.put("myString", "rob");

    object.saveInBackground(new SaveCallback () {
      @Override
      public void done(ParseException ex) {
        if (ex == null) {
          Log.i("Parse Result", "Successful!");
        } else {
          Log.i("Parse Result", "Failed" + ex.toString());
        }
      }
    });

    object.put("myNumber", "456");
    object.put("myString", "perci");

    object.saveInBackground(new SaveCallback () {
      @Override
      public void done(ParseException ex) {
        if (ex == null) {
          Log.i("Parse Result", "Successful!");
        } else {
          Log.i("Parse Result", "Failed" + ex.toString());
        }
      }
    });


    ParseObject object2 = new ParseObject("Object2");
    object2.put("name","Muniba");
    object2.put("number", 123);

    object2.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if(e == null){
          Log.i("Parse Result","Successful!");
        } else {
          Log.i("Parse Result", "Failed" + e.toString());
        }
      }
    });*/



   // ParseUser.enableAutomaticUser();

    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

  }
}
