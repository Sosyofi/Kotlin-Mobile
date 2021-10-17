package com.alirizakaygusuz.sosyofi.service

import com.alirizakaygusuz.sosyofi.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SosyofiAPIMainReply(
    @SerializedName("user")
    @Expose
    var user: User,
    @SerializedName("users")
    @Expose
    var followedUserList: List<User>,
    @SerializedName("success")
    @Expose
    var success: Int,
    @SerializedName("message")
    @Expose
    var message: String
)
