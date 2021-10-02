package com.alirizakaygusuz.sosyofi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alirizakaygusuz.sosyofi.databinding.ActivityUserMainBinding

class UserMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}