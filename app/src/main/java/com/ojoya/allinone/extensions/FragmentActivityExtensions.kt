package com.ojoya.allinone.extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.showToast(@StringRes resId: Int) {
    runOnUiThread {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show()
    }
}

fun FragmentActivity.showToast(text: String) {
    runOnUiThread {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}