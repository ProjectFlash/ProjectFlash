package com.rkpandey.quizlet_wireframe

import android.app.Application
import com.parse.Parse
import com.parse.ParseObject
import java.util.logging.Level.parse

class ParseApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        // Register Parse Model
        ParseObject.registerSubclass(Post::class.java)

        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());
    }
}