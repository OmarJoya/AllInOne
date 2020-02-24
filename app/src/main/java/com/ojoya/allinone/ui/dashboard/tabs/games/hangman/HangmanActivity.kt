package com.ojoya.allinone.ui.dashboard.tabs.games.hangman

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ojoya.allinone.R
import com.ojoya.allinone.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class HangmanActivity : BaseActivity(), HasSupportFragmentInjector, HangmanGameFragment.Companion.Listener,
    GameResultFragment.Companion.Listener,
    InitialConfigFragment.Companion.Listener {

    @Inject
    lateinit var hangmanViewModel: HangmanViewModel

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hangman)

        hangmanViewModel = ViewModelProvider(this, viewModelFactory).get(HangmanViewModel::class.java)

        fragmentContainer = R.id.container

        navigateToFragment(InitialConfigFragment.newInstance())

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.hangman)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()

        return super.onOptionsItemSelected(item)
    }

    override fun onWin() {
        navigateToFragment(GameResultFragment.newInstance(true))
    }

    override fun onLose() {
        navigateToFragment(GameResultFragment.newInstance(false))
    }

    override fun onNewGame() {
        navigateToFragment(InitialConfigFragment.newInstance())
    }

    override fun onNextWord() {
        navigateToFragment(HangmanGameFragment.newInstance())
    }

    override fun onStartGame() {
        navigateToFragment(HangmanGameFragment.newInstance())
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, HangmanActivity::class.java)
    }
}