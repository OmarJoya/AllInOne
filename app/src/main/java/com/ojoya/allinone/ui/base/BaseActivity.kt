package com.ojoya.allinone.ui.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ojoya.allinone.di.ViewModelFactory
import com.ojoya.allinone.utils.AppSharedPreferences
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var appSharedPreferences: AppSharedPreferences
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected var disposable = CompositeDisposable()

    @IdRes
    var fragmentContainer: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

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