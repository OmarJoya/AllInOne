package com.ojoya.allinone.ui.dashboard.tabs.games.hangman

import android.app.ActionBar
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.LinearLayout
import com.ojoya.allinone.R
import com.ojoya.allinone.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_hangman_game.*
import java.util.*

class HangmanGameFragment : BaseFragment() {

    private val selectedLetters = mutableListOf<String>()
    private lateinit var word: String
    private var attempts = 0
    private lateinit var listener: Listener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is Listener) context else throw RuntimeException("Activity must implement this fragment's listener")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hangman_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var selectedCategory = RANDOM
        arguments?.getInt(CATEGORY)?.let {
            selectedCategory = it
        }
        val resourceId = when(selectedCategory) {
            ANIMALS -> R.array.hangman_animals
            COLORS -> R.array.hangman_colors
            COUNTRIES -> R.array.hangman_countries
            SPORTS -> R.array.hangman_sports
            else -> 0
        }

        val words = if (resourceId == RANDOM)
            mutableListOf<String>().apply {
                addAll(resources.getStringArray(R.array.hangman_animals))
                addAll(resources.getStringArray(R.array.hangman_colors))
                addAll(resources.getStringArray(R.array.hangman_countries))
                addAll(resources.getStringArray(R.array.hangman_sports))
            }
        else
            resources.getStringArray(resourceId).toMutableList()

        word = words[Random().nextInt(words.size)].toUpperCase(Locale.US)

        arguments?.get(SCORE)?.let {
            scoreTextView.text = getString(R.string.score, it)
        }

        createAlphabetButtons()
        evaluateSelectedLetters()
    }

    private fun createAlphabetButtons() {
        val alphabet = resources.getString(R.string.alphabet)

        alphabetContainer.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                alphabetContainer.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val columnWidth = alphabetContainer.width / alphabetContainer.columnCount
                for (letter in alphabet.toCharArray()) {
                    val button = createLetterButton(columnWidth, letter)
                    alphabetContainer.addView(button)
                }
            }
        })
    }

    private fun evaluateSelectedLetters() {
        var wordWithUnderscores = ""
        for (letter in word.toCharArray()) {
            wordWithUnderscores += when {
                letter in selectedLetters.toString() -> String.format(" %s", letter)
                Character.isWhitespace(letter) -> "  "
                else -> " _"
            }
        }
        wordTextView.text = wordWithUnderscores
        if (!wordWithUnderscores.contains('_'))
            listener.onWin()
    }

    private fun createLetterButton(columnWidth: Int, letter: Char): Button {
        return Button(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(columnWidth, ActionBar.LayoutParams.WRAP_CONTENT)
            text = letter.toString()
            setOnClickListener(onClickLetterButton)
        }
    }

    private val onClickLetterButton = View.OnClickListener {
        val button = it as Button
        button.visibility = View.INVISIBLE
        val letter = button.text.toString()

        if (letter in word) {
            selectedLetters.add(letter)
            evaluateSelectedLetters()
        } else {
            attempts++
        }

        when (attempts) {
            1 -> hangmanImageView.setImageResource(R.drawable.ic_hangman_head)
            2 -> hangmanImageView.setImageResource(R.drawable.ic_hangman_left_hand)
            3 -> hangmanImageView.setImageResource(R.drawable.ic_hangman_right_hand)
            4 -> hangmanImageView.setImageResource(R.drawable.ic_hangman_left_feet)
            5 -> listener.onLose()
        }
    }

    companion object {
        private const val SCORE = "Score"
        private const val CATEGORY = "Category"
        const val RANDOM = 0
        private const val ANIMALS = 1
        private const val COLORS = 2
        private const val COUNTRIES = 3
        private const val SPORTS = 4

        interface Listener {
            fun onWin()
            fun onLose()
        }

        fun newInstance(score: Int, selectedCategory: Int): HangmanGameFragment {
            return HangmanGameFragment().apply {
                arguments = Bundle().apply {
                    putInt(SCORE, score)
                    putInt(CATEGORY, selectedCategory)
                }
            }
        }
    }
}