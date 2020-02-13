package com.ojoya.allinone.ui.dashboard.tabs.games.hangman

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.ojoya.allinone.R
import com.ojoya.allinone.ui.base.BaseActivity

class HangmanActivity : BaseActivity(), HangmanGameFragment.Companion.Listener,
    GameResultFragment.Companion.Listener,
    InitialConfigFragment.Companion.Listener {

    private var selectedCategory: Int = HangmanGameFragment.RANDOM
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hangman)
        fragmentContainer = R.id.container

        navigateToFragment(InitialConfigFragment.newInstance())

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setTitle(R.string.hangman)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }

    override fun onWin() {
        score++
        navigateToFragment(GameResultFragment.newInstance(true))
    }

    override fun onLose() {
        score = 0
        navigateToFragment(GameResultFragment.newInstance(false))
    }

    override fun onNewGame() {
        navigateToFragment(InitialConfigFragment.newInstance())
    }

    override fun onNextWord() {
        navigateToFragment(HangmanGameFragment.newInstance(score, selectedCategory))
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, HangmanActivity::class.java)
    }

    override fun onStartGame(selectedCategory: Int) {
        this.selectedCategory = selectedCategory
        navigateToFragment(HangmanGameFragment.newInstance(score, selectedCategory))
    }
}