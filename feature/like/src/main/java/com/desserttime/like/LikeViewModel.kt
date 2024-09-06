package com.desserttime.like

import androidx.lifecycle.viewModelScope
import com.desserttime.core.base.BaseViewModel
import com.desserttime.domain.model.RequestSendAccusationData
import com.desserttime.domain.repository.LikeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
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
    override fun reduceState(currentState: LikeState, event: LikeEvent): LikeState =
        when (event) {
            is LikeEvent.RequestAccusationData -> {
                currentState.copy(allAccusations = event.allAccusations)
            }
            else -> currentState
        }

    // accusation data 받아오기
    fun requestAccusationData() {
        likeRepository.requestAllAccusations()
            .onEach {
                Timber.i("$TAG requestAccusationData: $it")
                sendAction(LikeEvent.RequestAccusationData(it.data))
            }
            .catch {
                // Error 처리
                Timber.e("$TAG $it")
            }
            .launchIn(viewModelScope)
    }

    fun requestSendAccusationData(selectedItems: List<String>) {
        Timber.i("$TAG requestSendAccusationData: $selectedItems")
        likeRepository.requestSendAccusation(
            RequestSendAccusationData(
                "ETC",
                "Test",
                1,
                1,
            )
        )
            .onEach {
                Timber.i("$TAG requestSendAccusationData: $it")
            }
            .catch {
                // Error 처리
                Timber.e("$TAG $it")
            }
            .launchIn(viewModelScope)
    }
}