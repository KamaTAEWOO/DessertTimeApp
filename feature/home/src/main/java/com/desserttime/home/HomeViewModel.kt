package com.desserttime.home

import androidx.lifecycle.viewModelScope
import com.desserttime.core.base.BaseViewModel
import com.desserttime.core.local.MemberDataStore
import com.desserttime.domain.model.MemberData
import com.desserttime.domain.repository.MemberInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

private const val TAG = "HomeViewModel::"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val memberInfoRepository: MemberInfoRepository,
    private val memberDataStore: MemberDataStore
) : BaseViewModel<HomeState, HomeEvent>(
    initialState = HomeState()
) {
    private val _memberData: Flow<MemberData> = memberInfoRepository.memberData
    val memberData: Flow<MemberData> = _memberData

    override fun reduceState(currentState: HomeState, event: HomeEvent): HomeState {
        TODO("Not yet implemented")
    }

    fun requestMemberData(memberId: String) {
        memberInfoRepository.requestMemberData(memberId)
            .onEach {
                Timber.d("requestMemberData: $it")
                memberDataStore.saveMemberData(it.data)
            }
            .catch { e ->
                Timber.e(e)
            }
            .launchIn(viewModelScope)
    }
}
