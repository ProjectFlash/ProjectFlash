package com.rkpandey.quizlet_wireframe

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseUser

// Word: String
// Definition: String
// Category: String
@ParseClassName("Post")
class Post : ParseObject() {
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

    companion object{
        const val KEY_WORD = "word"
        const val KEY_DEFINITION = "definition"
        const val KEY_CATEGORY = "category"
        const val KEY_USER = "user"
    }
}