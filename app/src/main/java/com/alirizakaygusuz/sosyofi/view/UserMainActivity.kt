package com.alirizakaygusuz.sosyofi.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.alirizakaygusuz.sosyofi.R
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

    private var user_id = 0



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityUserMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        user_id = intent?.getIntExtra("userId", 0)!!

        Log.i("SA", "Hİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİ:$user_id")
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.i("Sa-------------------------", "-----------------as")
        if (item.itemId == R.id.action_search) {

            Log.i("Sa-------------------------", "-----------------as")
            val searchView: SearchView = item.actionView as SearchView

            searchView.queryHint = "Kimi Bulmak İstiyorsun ? "

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.i("Query", query!!)

                    if(!query.isNullOrEmpty()){
                        Log.i("SAA", query)



                       val userMainFragment = UserMainFragment()
                        userMainFragment.getSearchedUsers(binding.navigationView , query , user_id ,applicationContext)
                    }

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false

                }

            })

        } else {
            Log.i("Ağla", "ağla ")
        }

        return super.onOptionsItemSelected(item)
    }



}