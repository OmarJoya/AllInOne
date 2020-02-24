package com.ojoya.allinone.ui.dashboard.tabs.games.hangman

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.lifecycle.ViewModelProvider

import com.ojoya.allinone.R
import com.ojoya.allinone.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_initial_config.*
import javax.inject.Inject

class InitialConfigFragment : BaseFragment() {

    @Inject
    lateinit var hangmanViewModel: HangmanViewModel
    private lateinit var listener: Listener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is Listener) context else throw RuntimeException("Activity must implement this fragment's listener")
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_initial_config, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hangmanViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(HangmanViewModel::class.java)

        startGameButton.setOnClickListener {
            listener.onStartGame()
        }

        categorySpinner.onItemSelectedListener = object: OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                hangmanViewModel.selectedCategory = position
            }
        }
    }

    companion object {

        fun newInstance(): InitialConfigFragment {
            return InitialConfigFragment()
        }

        interface Listener {
            fun onStartGame()
        }
    }
}
