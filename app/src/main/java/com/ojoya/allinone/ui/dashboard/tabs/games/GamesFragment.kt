package com.ojoya.allinone.ui.dashboard.tabs.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ojoya.allinone.R
import com.ojoya.allinone.ui.dashboard.model.Game
import kotlinx.android.synthetic.main.fragment_games.*

class GamesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val games = ArrayList<Game>()
        games.add(Game(HANGMAN, ""))

        val gamesAdapter = GamesAdapter(games)
        gamesAdapter.onClickListener = { game ->
        }

        gamesRecyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        gamesRecyclerView.adapter = gamesAdapter
    }

    companion object {
        fun newInstance(): GamesFragment {
            return GamesFragment()
        }
    }
}