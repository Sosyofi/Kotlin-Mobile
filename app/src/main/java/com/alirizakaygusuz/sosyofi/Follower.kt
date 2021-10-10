package com.alirizakaygusuz.sosyofi

import java.io.Serializable

data class  Follower(var user_id: Int = 0, var follower_id: Int = 0) : Serializable{
    constructor() : this(user_id = 0, follower_id = 0) {

    }


     var id: Int = 0
         get() {
            TODO()
        }





}