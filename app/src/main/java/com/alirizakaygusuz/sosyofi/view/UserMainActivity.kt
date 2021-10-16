package com.alirizakaygusuz.sosyofi.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alirizakaygusuz.sosyofi.adapter.UserAdapter
import com.alirizakaygusuz.sosyofi.databinding.ActivityUserMainBinding
import com.alirizakaygusuz.sosyofi.model.User
import com.alirizakaygusuz.sosyofi.service.SosyofiAPIMainReply
import com.alirizakaygusuz.sosyofi.util.SosyofiAPIUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserMainBinding
    private var sosyofiAPI = SosyofiAPIUtils.getSosyofiAPI()


    private lateinit var followerUserList: List<User>
    private lateinit var user: User

    private lateinit var adapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityUserMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = intent
        var user_id = intent.getIntExtra("userId", 0)



        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this@UserMainActivity)

        getUser(user_id)

    }

    fun click_imvUserMain(view: View) {
        val intent = Intent(this@UserMainActivity, BiographyActivity::class.java)
        Log.i("User:", user.toString())
        intent.putExtra("userInfo", user)
        startActivity(intent)
    }


    fun getUser(user_id: Int) {

        sosyofiAPI.fetchUserAllInfo(user_id).enqueue(object : Callback<SosyofiAPIMainReply> {
            override fun onResponse(
                call: Call<SosyofiAPIMainReply>?,
                response: Response<SosyofiAPIMainReply>?,
            ) {
                if (response != null) {

                    val responseBody = response.body()

                    responseBody?.user?.let {

                        user = it
                        binding.txtUserNickname.text = user.nickname

                        binding.txtUserInfo.text = user.bio
                        binding.txtUserFollower.text = user.followers_count.toString()
                        binding.txtUserFollowed.text = user.followed_count.toString()


                    }

                    responseBody?.followedUserList?.let {
                        followerUserList = responseBody?.followedUserList
                        Log.i("Sa", "as")

                        adapter = UserAdapter(this@UserMainActivity, followerUserList)
                        Log.i("Liste:", followerUserList.toString())
                        binding.recyclerView.adapter = adapter
                    }


                }
            }

            override fun onFailure(call: Call<SosyofiAPIMainReply>?, t: Throwable?) {
                Log.i("UserMainActivty Failled:", t?.message.toString())
            }

        })
    }
}