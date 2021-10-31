package com.alirizakaygusuz.sosyofi.service

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TwitterReply(
    @SerializedName("text")
    @Expose
    var twitList: List<String>
)