package io.moatwel.android.sample.uistatesample.extensions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar


val AppCompatActivity.viewLifecycleOwner: LifecycleOwner
    get() = this

fun AppCompatActivity.showMessage(
    parentView: View,
    message: String,
    onShown: () -> Unit,
) {
    val snackBar = Snackbar.make(parentView, message, Snackbar.LENGTH_SHORT)
    snackBar.show()
    onShown.invoke()
}
