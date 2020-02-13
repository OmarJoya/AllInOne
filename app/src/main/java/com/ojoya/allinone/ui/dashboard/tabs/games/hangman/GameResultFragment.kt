package com.ojoya.allinone.ui.dashboard.tabs.games.hangman

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ojoya.allinone.R
import kotlinx.android.synthetic.main.fragment_game_result.*

class GameResultFragment : Fragment() {

    private var didWin: Boolean = false
    private lateinit var listener: Listener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is Listener) context else throw RuntimeException("Activity must implement this fragment's listener")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getBoolean(DID_WIN, false)?.let {
            didWin = it
        }

        resultTextView.text = if (didWin) getString(R.string.you_win) else getString(R.string.you_lose)
        actionButton.text = if (didWin) getString(R.string.next_word) else getString(R.string.new_game)

        if (didWin) {
            resultTextView.text = getString(R.string.you_win)
            actionButton.text = getString(R.string.next_word)
        } else {
            resultTextView.text = getString(R.string.you_lose)
            actionButton.text = getString(R.string.new_game)
            hangmanImageView.setImageResource(R.drawable.ic_hangman_right_feet)
        }

        actionButton.setOnClickListener {
            if (didWin)
                listener.onNextWord()
            else
                listener.onNewGame()
        }
    }


    companion object {
        private const val DID_WIN = "DidWin"

        fun newInstance(didWin: Boolean) : GameResultFragment {
            return GameResultFragment().apply {
                arguments =  Bundle().apply {
                    putBoolean(DID_WIN, didWin)
                }
            }
        }

        interface Listener {
            fun onNewGame()
            fun onNextWord()
        }
    }
}