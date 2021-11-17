package com.alirizakaygusuz.sosyofi.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.alirizakaygusuz.sosyofi.R
import com.alirizakaygusuz.sosyofi.adapter.UserAdapter
import com.alirizakaygusuz.sosyofi.databinding.FragmentUserMainBinding
import com.alirizakaygusuz.sosyofi.databinding.FragmentUsersBinding
import com.alirizakaygusuz.sosyofi.model.User
import com.alirizakaygusuz.sosyofi.service.SosyofiAPIMainReply
import com.alirizakaygusuz.sosyofi.util.SosyofiAPIUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private var sosyofiAPI = SosyofiAPIUtils.getSosyofiAPI()
    private lateinit var adapter: UserAdapter
    private var user_id: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.recyclerVwFUsers.setHasFixedSize(true)
        binding.recyclerVwFUsers.layoutManager = LinearLayoutManager(context)


        var query: String? = null

        arguments?.let {
            query =
                UsersFragmentArgs.fromBundle(
                    it).query
            user_id = UsersFragmentArgs.fromBundle(
                it).userId
        }

        if (query != null) {
            searchUser(query!!)
        }


        //val intent = activity?.intent
        //val query = intent?.getStringExtra("query")

        /* Log.i("Query", query!!)

         if(!query.isNullOrEmpty()){
             searchUser(query)
         }*/
    }


    fun searchUser(nickname: String) {
        sosyofiAPI.searchUser(nickname).enqueue(object : Callback<SosyofiAPIMainReply> {
            override fun onResponse(
                call: Call<SosyofiAPIMainReply>?,
                response: Response<SosyofiAPIMainReply>?,
            ) {
                if (response != null) {

                    val responseBody = response.body()
                    if (responseBody?.success == 1) {

                        val userList: List<User> = responseBody.followedUserList

                        adapter = UserAdapter(requireContext(), userList, user_id)
                        Log.i("Liste:", userList.toString())
                        binding.recyclerVwFUsers.adapter = adapter

                        //Navigation.findNavController(view).navigate(action)
                        //Navigation.findNavController(view).navigate(action)

                    } else {
                        Toast.makeText(context,
                            "Aradığınız Kişi Bulunamamıştır!!",
                            Toast.LENGTH_SHORT)
                    }
                }
            }

            override fun onFailure(call: Call<SosyofiAPIMainReply>?, t: Throwable?) {
                Log.i("UserMainActivty Searc User Failled:", t?.message.toString())
            }

        })
    }


}