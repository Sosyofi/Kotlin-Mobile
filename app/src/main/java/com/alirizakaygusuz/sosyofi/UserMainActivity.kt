package com.alirizakaygusuz.sosyofi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alirizakaygusuz.sosyofi.databinding.ActivityUserMainBinding

class UserMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserMainBinding
    private  lateinit var followers:ArrayList<Followers>
    private lateinit var adapter: UserAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.layoutManager = LinearLayoutManager(this@UserMainActivity)



        followers = ArrayList()
        val follower :Followers = Followers("İPHonedo" , "264.372" , true , true , false ,true)
        val follower1 :Followers = Followers("Toyota" , "172.042" , true , false , true ,false)
        val follower2 :Followers = Followers("Metin" , "25.078" , true , true , true ,true)
        val follower3 :Followers = Followers("Sadık" , "21" , true , true , true ,true)
        val follower4 :Followers = Followers("Barış" , "2333" , true , true , true ,true)

        followers = arrayListOf(follower , follower1 , follower2 , follower3 , follower4)



        adapter = UserAdapter(this@UserMainActivity ,followers)

        binding.recyclerView.adapter = adapter


    }
}