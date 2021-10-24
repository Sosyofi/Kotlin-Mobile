package com.alirizakaygusuz.sosyofi.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.alirizakaygusuz.sosyofi.R
import com.alirizakaygusuz.sosyofi.databinding.ActivitySocialBinding
import com.alirizakaygusuz.sosyofi.databinding.FragmentTwitchBinding
import com.alirizakaygusuz.sosyofi.databinding.FragmentUnsplashBinding
import com.alirizakaygusuz.sosyofi.model.User
import com.alirizakaygusuz.sosyofi.service.SosyofiAPIMainReply
import com.alirizakaygusuz.sosyofi.util.SosyofiAPIUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UnsplashFragment : Fragment() {

    private var sosyofiAPI = SosyofiAPIUtils.getSosyofiAPI()


    private lateinit var binding: FragmentUnsplashBinding


    private lateinit var user: User



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUnsplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val intent = activity?.intent
        val followed_userId = intent?.getIntExtra("follewedUserId", 0)

        getUser(followed_userId!!)




        binding.imgvUnsplashRight.setOnClickListener {
            val action =
                com.alirizakaygusuz.sosyofi.view.UnsplashFragmentDirections.actionUnsplashFragmentToTwitchFragment(
                    user)
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun initUser(user: User) {
        this.user = user
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
                        initUser(it)

                        binding.txtUnsplash.text = it.unsplash

                    }
                }
            }

            override fun onFailure(call: Call<SosyofiAPIMainReply>?, t: Throwable?) {
                Log.i("UnsplashFragment Failled:", t?.message.toString())
            }
        })
    }




}