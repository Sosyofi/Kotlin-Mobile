package com.alirizakaygusuz.sosyofi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.alirizakaygusuz.sosyofi.databinding.ActivityBiographyBinding

class BiographyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiographyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiographyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = intent
        val user: User = intent.getSerializableExtra("userInfo") as User


        binding.txtBioTwitch.setText(user.twitch , TextView.BufferType.EDITABLE)
        binding.txtBioInstagram.setText(user.instagram , TextView.BufferType.EDITABLE)
        binding.txtBioTwitter.setText(user.twitter , TextView.BufferType.EDITABLE)
        binding.txtBioUnsplash.setText(user.unsplash , TextView.BufferType.EDITABLE)

        //karakrter karakater al alt alta yazdır

        val separateNickname = user.nickname.substring(0 , user.nickname.length)




        var greetNickname = "M\ne\nr\nh\na\nb\na\n\n\n"
        for(element in separateNickname){
            greetNickname += "\n${element}"
        }

        binding.txtBioRight.text = ""
        binding.txtBioRight.text = greetNickname



        binding.btnBioOnayla.setOnClickListener {
            user.twitch = binding.txtBioTwitch.text.toString()
            user.instagram = binding.txtBioInstagram.text.toString()
            user.twitter = binding.txtBioTwitter.text.toString()
            user.unsplash = binding.txtBioUnsplash.text.toString()
        }
    }
}