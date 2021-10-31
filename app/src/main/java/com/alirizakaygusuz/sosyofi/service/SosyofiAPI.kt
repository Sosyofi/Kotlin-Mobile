package com.alirizakaygusuz.sosyofi.service


import retrofit2.Call
import retrofit2.http.*


interface SosyofiAPI {

    //metodlarım
    //ekle-güncelle-sil- verileri çek vs vs
    @POST("includes/mobile_signup.inc.php")
    @FormUrlEncoded//türkçe karakter desteği
    fun userRegister(
        @Field("nickname") nickname: String ,
        @Field("first_name") first_name: String ,
        @Field("last_name") last_name: String,
        @Field("email") email: String ,
        @Field("password") hashed_password: String

    ): Call<SosyofiAPIReply>



    @POST("includes/mobile_login.inc.php")
    @FormUrlEncoded
    fun userLogin(
        @Field("email") email: String ,
        @Field("password") hashed_password: String
    ):Call<SosyofiAPIReply>


    @GET("includes/mobile_main.php")
    fun fetchUserAllInfo(
        @Query("id") user_id: Int
    ):Call<SosyofiAPIMainReply>


    @POST("includes/mobile_follow.php")
    @FormUrlEncoded
    fun userFollow(
        @Field("id") user_id: Int,
        @Field("follower_id") followed_id: Int,
    ):Call<SosyofiAPIReply>

    @POST("includes/mobile_unfollow.php")
    @FormUrlEncoded
    fun userUnfollow(
        @Field("id") user_id: Int,
        @Field("follower_id") followed_id: Int,
    ):Call<SosyofiAPIReply>




    @POST("includes/mobile_profile.php")
    @FormUrlEncoded
    fun userUpdate(
        //@Field("picture") picture: Blob ,
        @Field("id") id: Int ,
        @Field("bio") bio: String ,
        @Field("instagram") instagram: String ,
        @Field("twitch") twitch: String ,
        @Field("twitter") twitter: String ,
        @Field("unsplash") unsplash: String
    ): Call<SosyofiAPIReply>



    @GET("includes/mobile_searchUser.php")
    fun searchUser(
        @Query("nickname") nickname: String
    ):Call<SosyofiAPIMainReply>


    @GET("includes/mobile_userTwitter.php")
    fun fetchAllTwits(
        @Query("twitter") twitter: String
    ):Call<TwitterReply>



    @POST("includes/mobile_profileDelete.php")
    @FormUrlEncoded
    fun userDelete(@Field("id") id: Int): Call<SosyofiAPIReply>

}