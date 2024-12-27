package com.desserttime.home

import com.desserttime.core.base.BaseViewModel
import com.desserttime.domain.repository.MemberInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "HomeViewModel::"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val memberInfoRepository: MemberInfoRepository
) : BaseViewModel<HomeState, HomeEvent>(
    initialState = HomeState()
) {
    override fun reduceState(currentState: HomeState, event: HomeEvent): HomeState {
        TODO("Not yet implemented")
    }
}
