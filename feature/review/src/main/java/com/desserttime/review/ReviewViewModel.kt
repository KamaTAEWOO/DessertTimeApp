package com.desserttime.review

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.desserttime.core.base.BaseViewModel
import com.desserttime.domain.model.MemberData
import com.desserttime.domain.model.ReviewWriteData
import com.desserttime.domain.repository.MemberInfoRepository
import com.desserttime.domain.repository.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository,
    private val memberInfoRepository: MemberInfoRepository
) : BaseViewModel<ReviewState, ReviewEvent>(
    initialState = ReviewState()
) {

    // 로컬 데이터 저장소에서 사용자 정보를 가져오는 Flow
    private val _memberData: Flow<MemberData> = memberInfoRepository.memberData

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

    override fun reduceState(currentState: ReviewState, event: ReviewEvent): ReviewState =
        when (event) {
            else -> currentState
        }

    fun saveReviewWriteData() {
        setLoading(true)

        viewModelScope.launch {
            try {
                // memberId와 uiState의 각 값들이 null인지 안전하게 확인
                val memberId = _memberData.firstOrNull()?.memberId ?: run {
                    setLoading(false)
                    Timber.e("Member data is null")
                    return@launch
                }

                val uiStateValue = uiState.value
                val storeName = uiStateValue.storeName ?: ""
                val menuName = uiStateValue.storeMenu ?: ""
                val dessertCategoryId = uiStateValue.storeCategoryId ?: 0
                val score = uiStateValue.storeScore ?: 0
                val ingredientId = uiStateValue.storeMaterialList ?: emptyList()
                val content = uiStateValue.storeContent ?: ""

                // ReviewWriteData 객체 생성
                val reviewWriteData = ReviewWriteData(
                    memberId = memberId,
                    reviewId = 4, // reviewId를 동적으로 할당해야 하는 경우 적절히 수정
                    storeName = storeName,
                    menuName = menuName,
                    dessertCategoryId = dessertCategoryId,
                    score = score,
                    ingredientId = ingredientId,
                    content = content,
                    status = true
                )

                // 리뷰 데이터 저장
                reviewRepository.saveReviewWriteData(reviewWriteData).collect { isSuccess ->
                    setLoading(false)
                    isSuccess.success.let {
                        if (it) {
                            Timber.i("Review save success")
                        } else {
                            Timber.i("Review save fail")
                        }
                    }
                }
            } catch (e: Exception) {
                // 예외 발생 시 로딩 종료 및 에러 처리
                setLoading(false)
                Timber.e("Failed to save review: ${e.localizedMessage}")
            }
        }
    }
}
