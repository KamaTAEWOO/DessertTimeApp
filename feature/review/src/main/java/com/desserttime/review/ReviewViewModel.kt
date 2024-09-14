package com.desserttime.review

import com.desserttime.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor() : BaseViewModel<ReviewState, ReviewEvent>(
    initialState = ReviewState()
) {
    override fun reduceState(currentState: ReviewState, event: ReviewEvent): ReviewState =
        when (event) {
            else -> currentState
        }
}
