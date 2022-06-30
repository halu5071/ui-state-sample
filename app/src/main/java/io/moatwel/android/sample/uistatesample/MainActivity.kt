package io.moatwel.android.sample.uistatesample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import io.moatwel.android.sample.uistatesample.compose.ComposeUserListActivity
import io.moatwel.android.sample.uistatesample.databinding.ActivityMainBinding
import io.moatwel.android.sample.uistatesample.widget.WidgetUserListActivity


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.widgetUserList.setOnClickListener {
            WidgetUserListActivity.start(this)
        }

        binding.composeUserList.setOnClickListener {
            ComposeUserListActivity.start(this)
        }
    }
}
