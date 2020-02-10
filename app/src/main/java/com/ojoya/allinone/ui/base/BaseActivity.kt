package com.ojoya.allinone.ui.base

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity() {

    @IdRes
    var fragmentContainer: Int? = null

    fun navigateToFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        fragmentContainer?.let { container ->
            val transaction = supportFragmentManager.beginTransaction().apply {
                replace(container, fragment)
                if (addToBackStack)
                    addToBackStack(fragment.javaClass.simpleName)
            }
            transaction.commit()
        }
    }
}