package com.ojoya.allinone.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.ojoya.allinone.R
import com.ojoya.allinone.ui.dashboard.adapters.NavViewPager
import com.ojoya.allinone.ui.dashboard.tabs.games.GamesFragment
import com.ojoya.allinone.ui.dashboard.tabs.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class DashboardActivity : AppCompatActivity() {

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
        dashBoardViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        })

    }
}