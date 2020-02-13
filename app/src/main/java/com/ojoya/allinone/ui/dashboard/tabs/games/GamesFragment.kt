package com.ojoya.allinone.ui.dashboard.tabs.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ojoya.allinone.R
import com.ojoya.allinone.model.Game
import com.ojoya.allinone.ui.dashboard.tabs.games.hangman.HangmanActivity
import kotlinx.android.synthetic.main.fragment_games.*

class GamesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val games = ArrayList<Game>()
        games.add(Game(HANGMAN_ID, getString(R.string.hangman), R.drawable.ic_hangman_right_feet))

        val gamesAdapter = GamesAdapter(games)
        gamesAdapter.onClickListener = { game ->
            when (game.id) {
                HANGMAN_ID -> startActivity(HangmanActivity.newIntent(requireContext()))
            }
        }

        gamesRecyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        gamesRecyclerView.adapter = gamesAdapter
    }

    companion object {
        private const val HANGMAN_ID = 0

        fun newInstance(): GamesFragment {
            return GamesFragment()
        }
    }
}