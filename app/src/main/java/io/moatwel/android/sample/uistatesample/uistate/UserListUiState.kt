package io.moatwel.android.sample.uistatesample.uistate

import io.moatwel.android.sample.uistatesample.model.FlashMessage
import io.moatwel.android.sample.uistatesample.model.User
import java.io.Serializable


data class UserListUiState(
    val isLoading: Boolean,
    val userList: ArrayList<User>,
    val flashMessage: FlashMessage?,
) : Serializable {

    companion object {
        val Initial = UserListUiState(
            isLoading = true,
            userList = arrayListOf(),
            flashMessage = null,
        )
    }
}
