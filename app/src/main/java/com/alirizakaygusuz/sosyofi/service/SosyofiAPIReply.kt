package com.alirizakaygusuz.sosyofi.service

import com.alirizakaygusuz.sosyofi.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SosyofiAPIReply(
    @SerializedName("user_id")
    @Expose
    var user_id: Int,
    @SerializedName("success")
    @Expose
    var success: Int,
    @SerializedName("message")
    @Expose
    var message: String

)