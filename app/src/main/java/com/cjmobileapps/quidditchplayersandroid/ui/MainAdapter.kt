package com.cjmobileapps.quidditchplayersandroid.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cjmobileapps.quidditchplayersandroid.databinding.RowPlayerBinding
import com.cjmobileapps.quidditchplayersandroid.network.models.Player

class MainAdapter(val players: List<Player>) : RecyclerView.Adapter<MainAdapter.MainAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapterHolder {
        val binding = RowPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainAdapterHolder(binding)
    }

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: MainAdapterHolder, position: Int) {
        holder.bind(players[position])
    }

    inner class MainAdapterHolder(private val binding: RowPlayerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            binding.player = player
            binding.executePendingBindings()
        }
    }
}
