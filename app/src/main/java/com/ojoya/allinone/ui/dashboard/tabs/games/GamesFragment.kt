package com.ojoya.allinone.ui.dashboard.tabs.games


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ojoya.allinone.R

/**
 * A simple [Fragment] subclass.
 */
class GamesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }


    companion object {
        fun newInstance(): GamesFragment {
            return GamesFragment()
        }
    }
}