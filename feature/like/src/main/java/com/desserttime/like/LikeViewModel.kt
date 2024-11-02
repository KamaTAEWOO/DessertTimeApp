package com.desserttime.like

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.desserttime.core.base.BaseViewModel
import com.desserttime.domain.model.RequestSendAccusationData
import com.desserttime.domain.repository.LikeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

private const val TAG = "LikeViewModel::"

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val likeRepository: LikeRepository
) : BaseViewModel<LikeState, LikeEvent>(
    initialState = LikeState()
) {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

    override fun reduceState(currentState: LikeState, event: LikeEvent): LikeState =
        when (event) {
            is LikeEvent.RequestAccusationData -> {
                currentState.copy(allAccusations = event.allAccusations)
            }
            else -> currentState
        }

    // accusation data 받아오기
    fun requestAccusationData() {
        setLoading(true)

        likeRepository.requestAllAccusations()
            .onEach {
                Timber.i("$TAG requestAccusationData: $it")
                sendAction(LikeEvent.RequestAccusationData(it.data))
            }
            .catch {
                // Error 처리
                Timber.e("$TAG $it")
            }
            .onCompletion {
                setLoading(false)
            }
            .launchIn(viewModelScope)
    }

    fun requestSendAccusationData(selectedItems: List<String>, content: String) {
        Timber.i("$TAG requestSendAccusationData: $selectedItems, $content")

        setLoading(true)

        likeRepository.requestSendAccusation(
            RequestSendAccusationData(
                selectedItems[0].ifEmpty { "" },
                if (selectedItems[0] == "기타(직접입력)") content else "",
                1,
                4
            )
        )
            .onEach {
                Timber.i("$TAG requestSendAccusationData: $it")
            }
            .catch {
                // Error 처리
                Timber.e("$TAG $it")
            }
            .onCompletion {
                setLoading(false)
            }
            .launchIn(viewModelScope)
    }
}
