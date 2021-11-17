package com.alirizakaygusuz.sosyofi.service

import com.alirizakaygusuz.sosyofi.model.Twitter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TwitterReply(
    @SerializedName("twits")
    @Expose
    var twitList: List<Twitter>
)