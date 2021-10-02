package com.alirizakaygusuz.sosyofi

import java.sql.Blob

data class User( val nickname: String , val first_name: String ,val last_name: String , val email: String ,  val hashed_password: String , )
{
    private val user_id: Int
        get() {
            TODO()
        }
    private val picture: Blob?
        get() {
            TODO()
        }



}