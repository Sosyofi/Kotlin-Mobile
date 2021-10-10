package com.alirizakaygusuz.sosyofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.alirizakaygusuz.sosyofi.databinding.ActivityBiographyBinding
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class BiographyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiographyBinding

    private val URL = "http://192.168.1.104/php-auth/PHP-Web/includes/mobile_profile.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiographyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = intent
        val user: User = intent.getSerializableExtra("userInfo") as User



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




        binding.btnBioOnayla.setOnClickListener {
            user.bio = binding.txtBioInfo.text.toString()
            user.twitch = binding.txtBioTwitch.text.toString()
            user.instagram = binding.txtBioInstagram.text.toString()
            user.twitter = binding.txtBioTwitter.text.toString()
            user.unsplash = binding.txtBioUnsplash.text.toString()
            updateBio(user)
        }


    }

    fun updateBio(user: User) {

        Log.i("Mailimiz ::::::::::", user.email)


        val request = object : StringRequest(Method.POST, URL, Response.Listener { reply ->

            Log.i("Search Reply:", reply)


        }, Response.ErrorListener { error -> error.printStackTrace() }) {

            override fun getParams(): MutableMap<String, String> {

                val params = HashMap<String, String>()

                params["email"] = user.email
                params["bio"] = user.bio
                params["twitch"] = user.twitch
                params["unsplash"] = user.unsplash
                params["instagram"] = user.instagram
                params["twitter"] = user.twitter

                return params
            }


        }

        Volley.newRequestQueue(this@BiographyActivity).add(request)
    }
}