package com.ojoya.allinone.ui.dashboard

import android.os.Bundle
import com.jakewharton.rxbinding2.support.v4.view.RxViewPager
import com.ojoya.allinone.R
import com.ojoya.allinone.ui.base.BaseActivity
import com.ojoya.allinone.ui.dashboard.adapters.NavViewPager
import com.ojoya.allinone.ui.dashboard.tabs.games.GamesFragment
import com.ojoya.allinone.ui.dashboard.tabs.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class DashboardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()
    }

    private fun setupViewPager() {
        val viewPagerAdapter = NavViewPager(supportFragmentManager)
        viewPagerAdapter.addFragment(GamesFragment.newInstance())
        viewPagerAdapter.addFragment(SettingsFragment.newInstance())
        dashBoardViewPager.adapter = viewPagerAdapter

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            return@setOnNavigationItemSelectedListener when (menuItem.itemId) {
                R.id.navigation_games -> {
                    dashBoardViewPager.currentItem = 0
                    true
                }
                R.id.navigation_config -> {
                    dashBoardViewPager.currentItem = 1
                    true
                }
                else -> false
            }
        }

        disposable.add(RxViewPager.pageSelections(dashBoardViewPager).subscribe {position ->
            bottomNavigationView.menu.getItem(position).isChecked = true
        })

    }
}