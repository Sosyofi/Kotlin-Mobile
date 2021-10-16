package com.alirizakaygusuz.sosyofi.util

import com.alirizakaygusuz.sosyofi.service.SosyofiAPI
import com.alirizakaygusuz.sosyofi.service.SosyofiAPIService


class SosyofiAPIUtils {

    companion object{

        val BASE_URL = "http://192.168.1.104/php-auth/PHP-Web/"

        fun getSosyofiAPI(): SosyofiAPI {

            return SosyofiAPIService.getClient(BASE_URL).create(SosyofiAPI::class.java)

        }

    }
}