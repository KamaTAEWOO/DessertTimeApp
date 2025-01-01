package com.desserttime.home

import androidx.lifecycle.viewModelScope
import com.desserttime.core.base.BaseViewModel
import com.desserttime.core.local.MemberDataStore
import com.desserttime.domain.model.MemberData
import com.desserttime.domain.repository.MemberInfoRepository
import com.desserttime.domain.repository.ReviewRepository
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
    private val reviewRepository: ReviewRepository,
    private val memberDataStore: MemberDataStore
) : BaseViewModel<HomeState, HomeEvent>(
    initialState = HomeState()
) {
    private val _memberData: Flow<MemberData> = memberInfoRepository.memberData
    val memberData: Flow<MemberData> = _memberData

    override fun reduceState(currentState: HomeState, event: HomeEvent): HomeState {
        return when (event) {
            is HomeEvent.ResponseHomeImageData -> {
                val updatedReviewImageData = currentState.reviewImageData.toMutableList().apply {
                    add(event.homeImageData)
                }
                currentState.copy(reviewImageData = updatedReviewImageData)
            }
        }
    }

    fun requestMemberData(memberId: String) {
        memberInfoRepository.requestMemberData(memberId)
            .onEach {
                Timber.d("requestMemberData: $it")
                memberDataStore.saveMemberData(it.data)
                requestHomeImageData(it.data.memberId)
            }
            .catch { e ->
                Timber.e(e)
            }
            .launchIn(viewModelScope)
    }

    private fun requestHomeImageData(memberId: Int) {
        reviewRepository.requestHomeImageData(memberId)
            .onEach {
                Timber.d("requestHomeImageData: $it")
                for (imageList in it.data) {
                    for (image in imageList.categoryReviewImgList) {
                        Timber.d("path: ${image.path}")
                        sendAction(HomeEvent.ResponseHomeImageData(image.path))
                    }
                }
            }
            .catch { e ->
                Timber.e(e)
            }
            .launchIn(viewModelScope)
    }
}
