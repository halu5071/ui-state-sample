package io.moatwel.android.sample.uistatesample.widget

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import io.moatwel.android.sample.uistatesample.databinding.ItemUserBinding
import io.moatwel.android.sample.uistatesample.model.User


class UserItemViewHolder(
    private val binding: ItemUserBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        user: User,
        onClickUser: ((User) -> Unit)?,
    ) {
        binding.userName.text = user.name
        binding.avatarImage.load(user.avatarUrl) {
            transformations(CircleCropTransformation())
        }
        binding.root.setOnClickListener {
            onClickUser?.invoke(user)
        }
    }
}
