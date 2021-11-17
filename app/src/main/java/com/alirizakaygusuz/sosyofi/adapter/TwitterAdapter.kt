package com.alirizakaygusuz.sosyofi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.alirizakaygusuz.sosyofi.R
import com.alirizakaygusuz.sosyofi.model.Twitter

class TwitterAdapter(private val mContext: Context, private val twits: List<Twitter>):
    RecyclerView.Adapter<TwitterAdapter.CardViewDesignHolder>(){

    inner class CardViewDesignHolder(view: View) : RecyclerView.ViewHolder(view) {

        var carVvTwitterRow: CardView
        var imvTwitter: ImageView
        var txtTwitterUserName: TextView
        var txtTwitterTwit: TextView

        init {
            carVvTwitterRow = view.findViewById(R.id.carVvTwitterRow)
            imvTwitter = view.findViewById(R.id.imvTwitter)
            txtTwitterUserName = view.findViewById(R.id.txtTwitterUserName)
            txtTwitterTwit = view.findViewById(R.id.txtTwitterTwit)


        }

    }

    override fun getItemCount(): Int {
        return twits.size
    }



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TwitterAdapter.CardViewDesignHolder {

        val design = LayoutInflater.from(mContext).inflate(R.layout.twitter_row, parent, false)
        return CardViewDesignHolder(design)
    }

    override fun onBindViewHolder(holder: TwitterAdapter.CardViewDesignHolder, position: Int) {
        val chosenTwitter: Twitter = twits[position]


        chosenTwitter.image_TwitUri?.let {
            holder.imvTwitter.setImageURI(it)

        }
        holder.txtTwitterUserName.text = chosenTwitter.twitter_userName
        holder.txtTwitterTwit.text = chosenTwitter.twit
    }

}