package io.moatwel.android.sample.uistatesample.widget

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.moatwel.android.sample.uistatesample.UserListViewModel
import io.moatwel.android.sample.uistatesample.databinding.ActivityWidgetUserListBinding
import io.moatwel.android.sample.uistatesample.extensions.collectOnStarted
import io.moatwel.android.sample.uistatesample.extensions.showMessage
import io.moatwel.android.sample.uistatesample.extensions.viewLifecycleOwner


@AndroidEntryPoint
class WidgetUserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWidgetUserListBinding

    private val viewModel: UserListViewModel by viewModels()

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(createIntent(activity))
        }

        private fun createIntent(activity: Activity): Intent {
            return Intent(activity, WidgetUserListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWidgetUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listAdapter = UserListAdapter().apply {
            onClickUser = click@{

            }
        }

        binding.recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@WidgetUserListActivity)
        }

        viewModel.uiState.collectOnStarted(viewLifecycleOwner) { uiState ->
            uiState.flashMessage?.let { flashMessage ->
                showMessage(binding.root, flashMessage.message) {
                    viewModel.consumeFlashMessage(flashMessage.id)
                }
            }

            binding.progress.isVisible = uiState.isLoading

            listAdapter.submitList(uiState.userList)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadUserList()
    }
}
