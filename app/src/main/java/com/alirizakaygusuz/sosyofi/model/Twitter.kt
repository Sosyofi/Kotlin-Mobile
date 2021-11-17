package com.alirizakaygusuz.sosyofi.model

import android.net.Uri
import android.widget.ImageView
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.net.URI

data class Twitter(
    @SerializedName("twitter_userName")
    @Expose
    var twitter_userName: String,

    @SerializedName("twit")
    @Expose
    var twit: String,

    @SerializedName("image_TwitUri")
    @Expose
    var image_TwitUri: Uri? = null,
) : Serializable {


    constructor() : this(twitter_userName = "", twit = "", image_TwitUri = null) {

    }


}