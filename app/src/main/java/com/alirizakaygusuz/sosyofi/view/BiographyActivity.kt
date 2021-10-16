package com.alirizakaygusuz.sosyofi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.alirizakaygusuz.sosyofi.databinding.ActivityBiographyBinding
import com.alirizakaygusuz.sosyofi.model.User
import com.alirizakaygusuz.sosyofi.service.SosyofiAPICRUDReply
import com.alirizakaygusuz.sosyofi.service.SosyofiAPIReply
import com.alirizakaygusuz.sosyofi.util.SosyofiAPIUtils
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import retrofit2.Call
import retrofit2.Callback

class BiographyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiographyBinding
    private var sosyofiAPI = SosyofiAPIUtils.getSosyofiAPI()

    private lateinit var user: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiographyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = intent
        user = intent.getSerializableExtra("userInfo") as User


        initBiographyFields()

    }


    fun initBiographyFields(){
        binding.txtBioInfo.setText(user.bio, TextView.BufferType.EDITABLE)
        binding.txtBioTwitch.setText(user.twitch, TextView.BufferType.EDITABLE)
        binding.txtBioInstagram.setText(user.instagram, TextView.BufferType.EDITABLE)
        binding.txtBioTwitter.setText(user.twitter, TextView.BufferType.EDITABLE)
        binding.txtBioUnsplash.setText(user.unsplash, TextView.BufferType.EDITABLE)

        //karakrter karakater al alt alta yazdır

        val separateNickname = user.nickname.substring(0, user.nickname.length)


        var greetNickname = "M\ne\nr\nh\na\nb\na\n\n\n"
        for (element in separateNickname) {
            greetNickname += "\n${element}"
        }

        //Null Kontrolü
        binding.txtBioFollowed.text = user.followed_count.toString()
        binding.txtBioFollowers.text = user.followers_count.toString()
        binding.txtBioRight.text = ""
        binding.txtBioRight.text = greetNickname
    }

    fun updateUserBio(user: User) {

        sosyofiAPI.userUpdate(user.user_id,user.bio!! , user.instagram!! , user.twitch!! , user.twitter!! , user.unsplash!!).enqueue(object: Callback<SosyofiAPICRUDReply>{
            override fun onResponse(
                call: Call<SosyofiAPICRUDReply>?,
                response: retrofit2.Response<SosyofiAPICRUDReply>?,
            ) {
                if(response != null){
                    val responseBody = response.body()

                    if(responseBody?.success == 1){
                        Toast.makeText(applicationContext , "Güncelleme işlemi tamamlanmıştır!!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@BiographyActivity, UserMainActivity::class.java)
                        intent.putExtra("userId", user.user_id)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this@BiographyActivity, "Biyogrofi güncelleme işlemi başarısız!!", Toast.LENGTH_LONG).show()
                    }
                }

            }

            override fun onFailure(call: Call<SosyofiAPICRUDReply>?, t: Throwable?) {
               Log.e("BiographyActivty Update Error:", t?.message.toString())
            }

        })



    }

    fun click_btnBioUpdateUser(view: View){
        user.bio = binding.txtBioInfo.text.toString()
        user.twitch = binding.txtBioTwitch.text.toString()
        user.instagram = binding.txtBioInstagram.text.toString()
        user.twitter = binding.txtBioTwitter.text.toString()
        user.unsplash = binding.txtBioUnsplash.text.toString()

        Log.i("twitter:", user.twitter.toString())

        updateUserBio(user)
    }


    fun click_btnBiodeleteUser(view: View) {
        Log.i("Silinecek id:", user.user_id.toString())

        sosyofiAPI.userDelete(user.user_id).enqueue(object : Callback<SosyofiAPICRUDReply>{
            override fun onResponse(
                call: Call<SosyofiAPICRUDReply>?,
                response: retrofit2.Response<SosyofiAPICRUDReply>?,
            ) {
                if(response != null){
                    if(response.body()?.success == 1){
                        Toast.makeText(this@BiographyActivity, "Hesabınızı silinmiştir", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@BiographyActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this@BiographyActivity, "Hesabınızı silme işlemi başarısız!!", Toast.LENGTH_LONG).show()
                    }

                }
            }

            override fun onFailure(call: Call<SosyofiAPICRUDReply>?, t: Throwable?) {
                Log.e("BiographyActivty Delete Error:", t?.message.toString())
            }


        })

    }
}