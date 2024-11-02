package com.desserttime.review

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.desserttime.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor() : BaseViewModel<ReviewState, ReviewEvent>(
    initialState = ReviewState()
) {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

    override fun reduceState(currentState: ReviewState, event: ReviewEvent): ReviewState =
        when (event) {
            else -> currentState
        }
}
