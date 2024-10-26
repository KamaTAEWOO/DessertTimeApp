package com.desserttime.home

import com.desserttime.core.base.BaseViewModel
import com.desserttime.domain.model.MemberData
import com.desserttime.domain.repository.MemberInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val memberInfoRepository: MemberInfoRepository
) : BaseViewModel<HomeState, HomeEvent>(
    initialState = HomeState()
) {
    // 로컬 데이터 저장소에서 사용자 정보를 가져오는 Flow
    private val _memberData: Flow<MemberData> = memberInfoRepository.memberData
    val memberData: Flow<MemberData> = _memberData

    override fun reduceState(currentState: HomeState, event: HomeEvent): HomeState {
        TODO("Not yet implemented")
    }
}