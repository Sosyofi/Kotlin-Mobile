package com.alirizakaygusuz.sosyofi.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.sql.Blob

data class User(

    @SerializedName("id")
    @Expose
    var user_id: Int,

    @SerializedName("nickname")
    @Expose
    var nickname: String,

    @SerializedName("first_name")
    @Expose
    var first_name: String,

    @SerializedName("last_name")
    @Expose
    var last_name: String,
    @SerializedName("email")
    @Expose
    var email: String,

    @SerializedName("password")
    @Expose
    var hashed_password: String,

    @SerializedName("picture")
    @Expose
    val picture: Blob?,

    @SerializedName("bio")
    @Expose
    var bio: String?,

    @SerializedName("instagram")
    @Expose
    var instagram: String?,

    @SerializedName("twitch")
    @Expose
    var twitch: String?,

    @SerializedName("twitter")
    @Expose
    var twitter: String?,

    @SerializedName("unsplash")
    @Expose
    var unsplash: String?,

    @SerializedName("followers_count")
    @Expose
    var followers_count: Int,

    @SerializedName("followed_count")
    @Expose
    var followed_count: Int

) : Serializable {


    constructor() :this( 0,"", " " , "", "", "",null , "", ""," ","","",0, 0 ) {

    }

    constructor(nickname: String,first_name: String , last_name: String , email: String , hashed_password: String) :this( 0,"", " " , "", "", "",null , "", ""," ","","",0, 0 ) {
        this.nickname = nickname
        this.first_name = first_name
        this.last_name = last_name
        this.email = email
        this.hashed_password = hashed_password
    }





}