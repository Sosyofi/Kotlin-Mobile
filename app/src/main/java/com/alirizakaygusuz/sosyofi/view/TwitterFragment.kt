package com.alirizakaygusuz.sosyofi.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.alirizakaygusuz.sosyofi.adapter.TwitterAdapter
import com.alirizakaygusuz.sosyofi.adapter.UserAdapter

import com.alirizakaygusuz.sosyofi.databinding.FragmentTwitterBinding
import com.alirizakaygusuz.sosyofi.model.Twitter
import com.alirizakaygusuz.sosyofi.model.User
import com.alirizakaygusuz.sosyofi.service.SosyofiAPIMainReply
import com.alirizakaygusuz.sosyofi.service.TwitterReply
import com.alirizakaygusuz.sosyofi.util.SosyofiAPIUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TwitterFragment : Fragment() {

    private lateinit var binding: FragmentTwitterBinding
    private lateinit var adapter: TwitterAdapter
    private lateinit var user: User
    private var sosyofiAPI = SosyofiAPIUtils.getSosyofiAPI()

    private lateinit var twitList: List<Twitter>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTwitterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerVwTwitter.setHasFixedSize(true)
        binding.recyclerVwTwitter.layoutManager = LinearLayoutManager(context)




        arguments?.let {
            user = com.alirizakaygusuz.sosyofi.view.TwitchFragmentArgs.fromBundle(
                it).user
        }



        binding.imgvTwitterLeft.setOnClickListener {
            val action =
                com.alirizakaygusuz.sosyofi.view.TwitterFragmentDirections.actionTwitterFragmentToUnsplashFragment(
                    user)
            Navigation.findNavController(it).navigate(action)


        }

        binding.imgvTwitterRight.setOnClickListener {
            val action =
                com.alirizakaygusuz.sosyofi.view.TwitterFragmentDirections.actionTwitterFragmentToUnsplashFragment(
                    user)
            Navigation.findNavController(it).navigate(action)

        }




        if (user.twitter.isNullOrEmpty()) {
            Toast.makeText(context, "Twitter Hesabı Bulunmamaktadır", Toast.LENGTH_LONG)
        } else {
            getTwitterPosts(user.twitter!!)
        }


    }


    fun getTwitterPosts(twitterAdress: String) {
        sosyofiAPI.fetchAllTwits(twitterAdress).enqueue(object : Callback<TwitterReply> {
            override fun onResponse(
                call: Call<TwitterReply>?,
                response: Response<TwitterReply>?,
            ) {
                if (response != null) {
                    val responseBody = response.body()

                    responseBody?.let {
                        Log.i(",Replyyyy", it.toString())

                        val twitList: List<Twitter> = responseBody.twitList

                        adapter = TwitterAdapter(requireContext(), twitList)
                        Log.i("Liste:", twitList.toString())
                        binding.recyclerVwTwitter.adapter = adapter


                    }


                }
            }

            override fun onFailure(call: Call<TwitterReply>?, t: Throwable?) {
                Log.i("UserMainActivty Failled:", t?.message.toString())
            }

        })
    }


}