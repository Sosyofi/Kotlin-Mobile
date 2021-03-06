package com.alirizakaygusuz.sosyofi.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.alirizakaygusuz.sosyofi.R
import com.alirizakaygusuz.sosyofi.model.User
import com.alirizakaygusuz.sosyofi.view.SocialActivity


class UserAdapter(private val mContext: Context, private val followers: List<User> , private val user_id: Int) :
    RecyclerView.Adapter<UserAdapter.CardViewDesignHolder>() {


    inner class CardViewDesignHolder(view: View) : RecyclerView.ViewHolder(view) {

        var cardVvUsersRow: CardView
        var txtRowUsersUserName: TextView
        var txtRowUserFollowersNumber: TextView
        var btnRowUsersTwitch: Button
        var btnRowUsersInstagram: Button
        var btnRowUsersTwitter: Button
        var btnRowUsersUnsplash: Button



        init {
            cardVvUsersRow = view.findViewById(R.id.carVvTwitterRow)
            txtRowUsersUserName = view.findViewById(R.id.txtRowUsersUserName)
            txtRowUserFollowersNumber = view.findViewById(R.id.txtRowUserFollowersNumber)
            btnRowUsersTwitch = view.findViewById(R.id.btnRowUsersTwitch)
            btnRowUsersInstagram = view.findViewById(R.id.btnRowUsersInstagram)
            btnRowUsersTwitter = view.findViewById(R.id.btnRowUsersTwitter)
            btnRowUsersUnsplash = view.findViewById(R.id.btnRowUsersUnsplash)


        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewDesignHolder {
        val design = LayoutInflater.from(mContext).inflate(R.layout.user_row, parent, false)
        return CardViewDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CardViewDesignHolder, position: Int) {



        val chosenFollower: User = followers[position]

        Log.i("Count:", followers.toString())

        holder.txtRowUserFollowersNumber.text = chosenFollower.followers_count.toString()
        holder.txtRowUsersUserName.text = chosenFollower.nickname

        //0
        chosenFollower.instagram.isNullOrEmpty().let {
            setVisiblity(it, holder.btnRowUsersInstagram)
        }


        //1
        chosenFollower.twitch.isNullOrEmpty().let {
            setVisiblity(it, holder.btnRowUsersTwitch)

        }

        //2
        chosenFollower.twitter.isNullOrEmpty().let {
            setVisiblity(it, holder.btnRowUsersTwitter)

        }


        //3
        chosenFollower.unsplash.isNullOrEmpty().let {
            setVisiblity(it, holder.btnRowUsersUnsplash)
        }


        //4
        holder.btnRowUsersTwitch.setOnClickListener {
            click_btnRowUsersTwitch(it, chosenFollower)
        }



        holder.cardVvUsersRow.setOnClickListener {

            var intent = Intent(mContext, SocialActivity::class.java)

           // if(user_id != -1){
                intent = Intent(mContext, SocialActivity::class.java)
                intent.putExtra("user_id", user_id)
            //}
            intent.putExtra("follewedUserId", chosenFollower.user_id)

            mContext?.startActivity(intent)
        }


    }


    fun setVisiblity(it: Boolean, btn: Button) {
        if (it) {
            btn.visibility = View.GONE
        } else {
            btn.visibility = View.VISIBLE
        }
    }

    fun click_btnRowUsersTwitch(view: View, chosenFollower: User) {
        //kontrol sa??lanmal?? !!!

    }


    override fun getItemCount(): Int {
        return followers.size
    }
}