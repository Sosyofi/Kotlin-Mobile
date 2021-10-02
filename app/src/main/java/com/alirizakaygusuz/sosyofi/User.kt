package com.alirizakaygusuz.sosyofi

import java.io.Serializable
import java.sql.Blob

data class User( var nickname: String = ""  , var first_name: String = "" ,var last_name: String = "", var email: String,  var hashed_password: String  ): Serializable
{


    constructor() : this(email = "", hashed_password = "") {

    }



    private val user_id: Int
        get() {
            TODO()
        }
    private val picture: Blob?
        get() {
            TODO()
        }



}