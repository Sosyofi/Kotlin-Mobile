package com.alirizakaygusuz.sosyofi.service

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SosyofiAPICRUDReply(
    @SerializedName("success")
    @Expose
    var success: Int ,
    @SerializedName("message")
    @Expose
    var message: String
)