package com.alirizakaygusuz.sosyofi

import java.io.Serializable
import java.sql.Blob

data class User(
    var nickname: String = "",
    var first_name: String = "",
    var last_name: String = "",
    var email: String = "",
    var hashed_password: String = "",
    var instagram: String = "",
    var twitch: String = "",
    var twitter: String = "",
    var unsplash: String = "",
    var bio: String = "",
    var followers_count: Int = 0,
    var followed_count: Int = 0,
) : Serializable {


    constructor() : this(email = "", hashed_password = "") {

    }


    var user_id: Int = 0
    fun get(): Int {
        return user_id
    }

    fun set(id: Int) {
        user_id = id
    }


    private val picture: Blob?
        get() {
            TODO()
        }


}