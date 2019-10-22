package com.cjmobileapps.quidditchplayersandroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cjmobileapps.quidditchplayersandroid.R
import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_player.view.*

class MainAdapter(val players: List<Player>) : RecyclerView.Adapter<MainAdapter.MainAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            MainAdapterHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_player, parent, false))

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: MainAdapterHolder, position: Int) {
        holder.bind(players[position])
    }

    inner class MainAdapterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(player: Player) {

            Picasso.get().load(player.imageUrl).into(itemView.playerRow_image)
            itemView.playerRow_name.text = player.fullName
            itemView.playerRow_position.text = player.positionName
            itemView.playerRow_favoriteSubjectText.text = player.favoriteSubject
            itemView.playerRow_yearsPlayedText.text = player.yearsPlayed.toString()
            itemView.playerRow_statusText.text = player.status
        }
    }
}
