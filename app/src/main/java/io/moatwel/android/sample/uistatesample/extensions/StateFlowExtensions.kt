package io.moatwel.android.sample.uistatesample.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


fun <T> StateFlow<T>.collectOnStarted(
    lifecycleOwner: LifecycleOwner,
    observer: (T) -> Unit,
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@collectOnStarted.collect {
                observer(it)
            }
        }
    }
}
