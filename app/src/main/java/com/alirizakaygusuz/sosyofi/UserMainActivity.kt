package com.alirizakaygusuz.sosyofi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.alirizakaygusuz.sosyofi.databinding.ActivityUserMainBinding

class UserMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserMainBinding
    private  lateinit var followerUserList:ArrayList<User>

    private lateinit var adapter: UserAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = intent
        val user: User = intent.getSerializableExtra("user") as User
        followerUserList = intent.getSerializableExtra("followerUserList") as ArrayList<User>

        for(f in followerUserList){
            Log.i("Gelen:", f.toString())
        }

        Log.e("Selam ehe:",user.toString())

        binding.recyclerView.setHasFixedSize(true)

        binding.recyclerView.layoutManager = LinearLayoutManager(this@UserMainActivity)








        adapter = UserAdapter(this@UserMainActivity ,followerUserList)

        binding.recyclerView.adapter = adapter




        binding.txtUserFollowed.text = user.followed_count.toString()
        binding.txtUserFollower.text =  user.followers_count.toString()
        binding.txtUserInfo.text =  user.bio
        binding.txtUserNickname.text =  user.nickname



        binding.imvUserMain.setOnClickListener {
            val intent = Intent(this@UserMainActivity, BiographyActivity::class.java)
            intent.putExtra("userInfo",user)
            startActivity(intent)
        }




    }



}