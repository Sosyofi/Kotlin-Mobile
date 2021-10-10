package com.alirizakaygusuz.sosyofi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter (private val mContext: Context, private val followers: ArrayList<User>): RecyclerView.Adapter<UserAdapter.CardViewDesignHolder>() {

    inner class CardViewDesignHolder(view: View): RecyclerView.ViewHolder(view) {

        var cardVvUsersRow: CardView
        var txtRowUsersUserName: TextView
        var txtRowUserFollowersNumber: TextView

        init {
            cardVvUsersRow = view.findViewById(R.id.cardVvUsersRow)
            txtRowUsersUserName = view.findViewById(R.id.txtRowUsersUserName)
            txtRowUserFollowersNumber = view.findViewById(R.id.txtRowUserFollowersNumber)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewDesignHolder {
        val design = LayoutInflater.from(mContext).inflate(R.layout.user_row , parent , false)
        return CardViewDesignHolder(design)
    }

    override fun onBindViewHolder(holder: CardViewDesignHolder, position: Int) {

        val follower = followers[position]


        holder.txtRowUserFollowersNumber.text = follower.followers_count.toString()
        holder.txtRowUsersUserName.text = follower.nickname



    }
    override fun getItemCount(): Int {
        return followers.size
    }
}