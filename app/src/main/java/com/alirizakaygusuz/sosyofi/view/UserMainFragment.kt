package com.alirizakaygusuz.sosyofi.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.alirizakaygusuz.sosyofi.R
import com.alirizakaygusuz.sosyofi.adapter.UserAdapter
import com.alirizakaygusuz.sosyofi.databinding.FragmentTwitchBinding
import com.alirizakaygusuz.sosyofi.databinding.FragmentUserMainBinding
import com.alirizakaygusuz.sosyofi.model.User
import com.alirizakaygusuz.sosyofi.service.SosyofiAPIMainReply
import com.alirizakaygusuz.sosyofi.util.SosyofiAPIUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserMainFragment : Fragment() {

    private lateinit var binding: FragmentUserMainBinding
    private var sosyofiAPI = SosyofiAPIUtils.getSosyofiAPI()


    private lateinit var followerUserList: List<User>
    private lateinit var user: User

    private lateinit var adapter: UserAdapter
    private var user_id = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUserMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val intent = activity?.intent

        user_id = intent?.getIntExtra("userId", 0)!!

        Log.i("SA",
            "Hİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİİ:$user_id")





        binding.recyclerViewMain.setHasFixedSize(true)
        binding.recyclerViewMain.layoutManager = LinearLayoutManager(context)

        binding.imvUserMain.setOnClickListener {
            click_imvUserMain(it)
        }

        user_id?.let {
            getUser(it)
        }

        binding.btnSearchSpecial.setOnClickListener {
            click_btnSearchSpeacial(it)
        }


    }


    fun click_imvUserMain(view: View) {
        val action =
            com.alirizakaygusuz.sosyofi.view.UserMainFragmentDirections.actionUserMainFragmentToBiographyFragment(
                user)
        view?.let { Navigation.findNavController(it).navigate(action) }
    }


    //Arayüzde gösterilemedi !!!!!!!
    fun click_btnSearchSpeacial(view: View) {

        var searchNickname = binding.txtSpeacialSearch.text.toString()
        var tempList = ArrayList<User>()

        if (!searchNickname.isNullOrEmpty()) {

            for (f in followerUserList) {

                if (f.nickname.equals(searchNickname)) {
                    tempList.add(f)

                }
            }

            if (!tempList.isNullOrEmpty()) {
                Log.i("Liste var mı ------------------------------------->:", tempList.toString())
                adapter.notifyDataSetChanged()
                adapter = UserAdapter(requireContext(), tempList, user_id)
                binding.recyclerViewMain.adapter = adapter

                adapter.notifyDataSetChanged()


            } else {
                Log.i("Liste yok ------------------------------------->:", tempList.toString())
                adapter = UserAdapter(requireContext(), tempList, user_id)
                binding.recyclerViewMain.adapter = adapter
                adapter.notifyDataSetChanged()
                Toast.makeText(context , "Aranan kişi Listede bulunamamıştır", Toast.LENGTH_SHORT).show()
            }
        } else {
            tempList.clear()
            adapter = UserAdapter(requireContext(), followerUserList, user_id)
            binding.recyclerViewMain.adapter = adapter
            adapter.notifyDataSetChanged()
            Toast.makeText(context , "Aramak İstediğiniz Kişiyi Yazınız", Toast.LENGTH_SHORT).show()

        }


    }


    fun setFollowedUserList(followedUserList: List<User>) {

        followerUserList = followedUserList


        adapter = UserAdapter(requireContext(), followerUserList, user_id)
        Log.i("Liste:", followerUserList.toString())
        binding.recyclerViewMain.adapter = adapter

        adapter.notifyDataSetChanged()

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
                        setFollowedUserList(responseBody?.followedUserList)


                    }

                }
            }

            override fun onFailure(call: Call<SosyofiAPIMainReply>?, t: Throwable?) {
                Log.i("UserMainActivty Failled:", t?.message.toString())
            }

        })
    }

    fun getSearchedUsers(view: View, query: String, user_id: Int, mContext: Context) {

        Log.i("Sa", "as")

        Log.i("Main user_id:", user_id.toString())

        if (user_id != 0) {

            val action =
                com.alirizakaygusuz.sosyofi.view.UserMainFragmentDirections.actionUserMainFragmentToUsersFragment(
                    query,
                    user_id)
            view?.let { Navigation.findNavController(it).navigate(action) }
        } else {
            Toast.makeText(mContext, "Hesabınıza Tekrardan Giriş Yapınız!!", Toast.LENGTH_LONG)
                .show()
        }


    }

}