package com.alirizakaygusuz.sosyofi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.alirizakaygusuz.sosyofi.databinding.ActivitySocialBinding
import com.alirizakaygusuz.sosyofi.service.SosyofiAPIMainReply
import com.alirizakaygusuz.sosyofi.service.SosyofiAPIReply
import com.alirizakaygusuz.sosyofi.util.SosyofiAPIUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SocialActivity : AppCompatActivity() {

    private var sosyofiAPI = SosyofiAPIUtils.getSosyofiAPI()


    private lateinit var  binding: ActivitySocialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val user_id = intent.getIntExtra("user_id", 0)
        val followed_userId = intent.getIntExtra("follewedUserId", 0)

        Log.i("EheE::", followed_userId.toString())
        Log.i("Krallll::", user_id.toString())

        getUser(followed_userId)

        binding.btnSocialFollowOrUnfollow.setOnClickListener {
            if(binding.btnSocialFollowOrUnfollow.text.equals("Takibi Bırak")){
                unfollow(user_id,followed_userId)
            }else{
                follow(user_id,followed_userId)
            }
        }


    }

    fun getUser(user_id: Int) {

        sosyofiAPI.fetchUserAllInfo(user_id).enqueue(object : Callback<SosyofiAPIMainReply> {
            override fun onResponse(
                call: Call<SosyofiAPIMainReply>?,
                response: Response<SosyofiAPIMainReply>?,
            ) {
                if (response != null) {

                    val responseBody = response.body()

                    Log.i("SAA: ", responseBody?.user.toString())

                    responseBody?.user?.let {

                        binding.txtSocialUserName.text = it.nickname
                        binding.txtSocailBio.text = it.bio
                        binding.txtSocialFollowed.text = it.followed_count.toString()
                        binding.txtSocialFollowers.text = it.followers_count.toString()

                    }



                }
            }

            override fun onFailure(call: Call<SosyofiAPIMainReply>?, t: Throwable?) {
                Log.i("SocialActivity Failled:", t?.message.toString())
            }

        })
    }


    fun follow(user_id: Int , followed_id: Int){
        sosyofiAPI.userFollow(user_id , followed_id).enqueue(object : Callback<SosyofiAPIReply> {
            override fun onResponse(
                call: Call<SosyofiAPIReply>?,
                response: Response<SosyofiAPIReply>?,
            ) {
                if(response != null){
                    if(response.body()?.success == 1){
                        binding.btnSocialFollowOrUnfollow.text = "Takibi Bırak"
                    }else{
                        Toast.makeText(this@SocialActivity , "Takip etmeyi tekrar deneyiniz!!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<SosyofiAPIReply>?, t: Throwable?) {
                Log.i("SocialActivity Follow Error:", t?.message.toString())
            }

        })
    }


    fun unfollow(user_id: Int , followed_id: Int){
        sosyofiAPI.userUnfollow(user_id , followed_id).enqueue(object : Callback<SosyofiAPIReply> {
            override fun onResponse(
                call: Call<SosyofiAPIReply>?,
                response: Response<SosyofiAPIReply>?,
            ) {
                if(response != null){
                    Log.i("Message", response.body()?.message.toString())
                    if(response.body()?.success == 1){
                        binding.btnSocialFollowOrUnfollow.text = "Takip Et"
                    }else{
                        Toast.makeText(this@SocialActivity , "Takipten çıkmayı tekrar deneyiniz!!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<SosyofiAPIReply>?, t: Throwable?) {
                Log.i("SocialActivity Unfollow Error:", t?.message.toString())
            }

        })
    }
}