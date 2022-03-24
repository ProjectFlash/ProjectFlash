package com.rkpandey.quizlet_wireframe

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseUser


@ParseClassName("Set")
class CardSet : Post(){

/*    fun getUser(): ParseUser?{
        return getParseUser(Post.KEY_USER)
    }

    fun setUser(user: ParseUser){
        put(Post.KEY_USER, user)
    }*/

    fun getLike(): Number?{
        return getNumber(Post.KEY_LIKE_COUNT)
    }

    fun setLike(like_count: Number){
        put(Post.KEY_LIKE_COUNT, like_count)
    }

    companion object{
        const val KEY_USER = "user"
        const val KEY_LIKE_COUNT = "like_count"
        const val KEY_SET_NAME = "set_name"
    }

}