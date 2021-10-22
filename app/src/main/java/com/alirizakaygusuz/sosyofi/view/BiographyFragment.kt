package com.alirizakaygusuz.sosyofi.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.alirizakaygusuz.sosyofi.R
import com.alirizakaygusuz.sosyofi.databinding.FragmentBiographyBinding
import com.alirizakaygusuz.sosyofi.databinding.FragmentUserMainBinding
import com.alirizakaygusuz.sosyofi.model.User
import com.alirizakaygusuz.sosyofi.service.SosyofiAPIReply
import com.alirizakaygusuz.sosyofi.util.SosyofiAPIUtils
import retrofit2.Call
import retrofit2.Callback


class BiographyFragment : Fragment() {

    private lateinit var binding: FragmentBiographyBinding
    private var sosyofiAPI = SosyofiAPIUtils.getSosyofiAPI()

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBiographyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            user = BiographyFragmentArgs.fromBundle(
                it
            ).user
        }




        initBiographyFields()

        binding.btnBioDeleteUser.setOnClickListener {
            click_btnBiodeleteUser(it)
        }

        binding.btnBioUpdateUser.setOnClickListener {
            click_btnBioUpdateUser(it)
        }
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

        sosyofiAPI.userUpdate(user.user_id,user.bio!! , user.instagram!! , user.twitch!! , user.twitter!! , user.unsplash!!).enqueue(object:
            Callback<SosyofiAPIReply> {
            override fun onResponse(
                call: Call<SosyofiAPIReply>?,
                response: retrofit2.Response<SosyofiAPIReply>?,
            ) {
                if(response != null){
                    val responseBody = response.body()

                    if(responseBody?.success == 1){
                        Toast.makeText(context , "Güncelleme işlemi tamamlanmıştır!!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, UserMainActivity::class.java)
                        intent.putExtra("userId", user.user_id)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(context, "Biyogrofi güncelleme işlemi başarısız!!", Toast.LENGTH_LONG).show()
                    }
                }

            }

            override fun onFailure(call: Call<SosyofiAPIReply>?, t: Throwable?) {
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

        sosyofiAPI.userDelete(user.user_id).enqueue(object : Callback<SosyofiAPIReply> {
            override fun onResponse(
                call: Call<SosyofiAPIReply>?,
                response: retrofit2.Response<SosyofiAPIReply>?,
            ) {
                if(response != null){
                    if(response.body()?.success == 1){
                        Toast.makeText(context, "Hesabınızı silinmiştir", Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(context, "Hesabınızı silme işlemi başarısız!!", Toast.LENGTH_LONG).show()
                    }

                }
            }

            override fun onFailure(call: Call<SosyofiAPIReply>?, t: Throwable?) {
                Log.e("BiographyActivty Delete Error:", t?.message.toString())
            }


        })

    }


}