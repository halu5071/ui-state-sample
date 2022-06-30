package io.moatwel.android.sample.uistatesample.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.moatwel.android.sample.uistatesample.R
import io.moatwel.android.sample.uistatesample.databinding.ItemUserBinding
import io.moatwel.android.sample.uistatesample.model.User
import java.lang.IllegalStateException


class UserListAdapter : ListAdapter<User, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    var onClickUser: ((User) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.item_user -> {
                UserItemViewHolder(
                    ItemUserBinding.inflate(inflater, parent, false)
                )
            }
            else -> throw IllegalStateException("Unknown view type.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is UserItemViewHolder -> {
                holder.bind(item, onClickUser)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_user
    }
}
