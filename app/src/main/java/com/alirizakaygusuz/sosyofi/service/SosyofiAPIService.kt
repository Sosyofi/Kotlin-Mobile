package com.alirizakaygusuz.sosyofi.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class SosyofiAPIService {

    companion object{

        val gson: Gson = GsonBuilder().setLenient().create()

        //BASEURL web servicelerimiz baş kısmıdır -> değişmeyen sabit olan kısımdır
        fun getClient(BASE_URL: String): Retrofit {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))//gson parse işleminin otomatik oalrka yapabildğimiz yapı
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
        }

    }

}