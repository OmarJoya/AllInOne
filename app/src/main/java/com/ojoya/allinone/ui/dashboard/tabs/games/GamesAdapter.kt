package com.ojoya.allinone.ui.dashboard.tabs.games

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ojoya.allinone.R
import com.ojoya.allinone.model.Game
import kotlinx.android.synthetic.main.view_game.view.*

class GamesAdapter(private val games: List<Game>) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    lateinit var onClickListener: (Game) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.view_game, parent, false), onClickListener)
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(games[position])

    class ViewHolder(itemView: View, val onClickListener: (Game) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun bind(game: Game) = with(itemView) {
            nameTextView.text = game.name
            setOnClickListener { onClickListener(game) }
            gameImageView.setImageResource(game.image)
        }
    }
}