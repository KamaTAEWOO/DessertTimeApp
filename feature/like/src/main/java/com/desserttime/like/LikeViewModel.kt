package com.desserttime.like

import com.desserttime.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "LikeViewModel::"

@HiltViewModel
class LikeViewModel @Inject constructor(

) : BaseViewModel<LikeState, LikeEvent>(
    initialState = LikeState()
) {
    override fun reduceState(currentState: LikeState, event: LikeEvent): LikeState =
        when (event) {
            is LikeEvent.RequestCategoryData -> {
                currentState.copy(test = event.test)
            }
            else -> currentState
        }

}