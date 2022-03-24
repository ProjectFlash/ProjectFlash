package com.rkpandey.quizlet_wireframe

import android.provider.Settings.Global.getString
import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseUser

// Word: String
// Definition: String
// Category: String
@ParseClassName("Post")
open class Post : ParseObject() {
    fun getWord(): String?{
        return getString(KEY_WORD)
    }

    fun setWord(word: String){
        put(KEY_WORD, word)
    }

    fun getDefinition(): String?{
        return getString(KEY_DEFINITION)
    }

    fun setDefinition(definition: String){
        put(KEY_DEFINITION, definition)
    }

    fun getCategory(): String? {
        return getString(KEY_CATEGORY)
    }

    fun setCategory(category: String){
        put(KEY_CATEGORY, category)
    }

    fun getUser(): ParseUser?{
        return getParseUser(KEY_USER)
    }

    fun setUser(user: ParseUser){
        put(KEY_USER, user)
    }

    fun getPassword(): String?{
        return getString(KEY_PASSWORD)
    }

    fun setPassword(password: String){
        put(KEY_PASSWORD, password)
    }



    fun getSetName(): String?{
        return getString(KEY_SET_NAME)
    }

    fun setSetName(set_name: String){
        put(KEY_SET_NAME, set_name)
    }
    
    // change to like count

    companion object{
        const val KEY_WORD = "word"
        const val KEY_DEFINITION = "definition"
        const val KEY_CATEGORY = "category"
        const val KEY_USER = "user"
        const val KEY_PASSWORD = "password"
        const val KEY_LIKE_COUNT = "like_count"
        const val KEY_SET_NAME = "set_name"
    }
}
