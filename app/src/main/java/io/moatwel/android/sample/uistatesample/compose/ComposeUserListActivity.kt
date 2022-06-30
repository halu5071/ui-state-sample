package io.moatwel.android.sample.uistatesample.compose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.moatwel.android.sample.uistatesample.UserListViewModel
import io.moatwel.android.sample.uistatesample.extensions.collectOnStarted
import io.moatwel.android.sample.uistatesample.extensions.viewLifecycleOwner


@AndroidEntryPoint
class ComposeUserListActivity : AppCompatActivity() {

    private val viewModel: UserListViewModel by viewModels()

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(createIntent(activity))
        }

        private fun createIntent(activity: Activity): Intent {
            return Intent(activity, ComposeUserListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.uiState.collectOnStarted(viewLifecycleOwner) { uiState ->
            println("Hoge: collect: $uiState")
            setContent {
                UiStateSampleTheme {
                    UserListScreen(
                        uiState = uiState,
                        onSnackBarShown = {
                            println("Hoge: onSnackBarShown")
                            viewModel.consumeFlashMessage(it)
                        }
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadUserList()
    }
}
