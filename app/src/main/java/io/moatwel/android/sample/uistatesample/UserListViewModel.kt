package io.moatwel.android.sample.uistatesample

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.moatwel.android.sample.uistatesample.extensions.getStateFlow
import io.moatwel.android.sample.uistatesample.model.FlashMessage
import io.moatwel.android.sample.uistatesample.model.Result
import io.moatwel.android.sample.uistatesample.repository.UserRepository
import io.moatwel.android.sample.uistatesample.uistate.UserListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class UserListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UserListUiState> =
        savedStateHandle.getStateFlow(viewModelScope, EXTRA_UI_STATE, UserListUiState.Initial)
    val uiState: StateFlow<UserListUiState> = _uiState

    companion object {
        private const val EXTRA_UI_STATE = "ui_state"
    }

    fun loadUserList() {
        _uiState.update { uiState ->
            uiState.copy(isLoading = true)
        }

        viewModelScope.launch {
            when (val result = userRepository.getUserList()) {
                is Result.Success -> {
                    _uiState.update { uiState ->
                        uiState.copy(
                            isLoading = false,
                            userList = ArrayList(result.value),
                        )
                    }
                }
                is Result.Failure -> {
                    _uiState.update { uiState ->
                        uiState.copy(
                            isLoading = false,
                            flashMessage = FlashMessage.create(
                                result.throwable.localizedMessage.orEmpty()
                            )
                        )
                    }
                }
            }
        }
    }

    fun consumeFlashMessage(uuid: UUID) {
        _uiState.update { uiState ->
            val message = if (uiState.flashMessage?.id == uuid) {
                null
            } else uiState.flashMessage

            uiState.copy(flashMessage = message)
        }
    }
}
