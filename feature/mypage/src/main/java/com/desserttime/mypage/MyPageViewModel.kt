package com.desserttime.mypage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.desserttime.core.base.BaseViewModel
import com.desserttime.domain.model.MemberData
import com.desserttime.domain.model.NickNameDoubleCheckData
import com.desserttime.domain.model.RequestMyPageMemberSaveData
import com.desserttime.domain.model.WithdrawalData
import com.desserttime.domain.repository.MemberInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

private const val TAG = "MyPageViewModel::"

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val memberInfoRepository: MemberInfoRepository
) : BaseViewModel<MyPageState, MyPageEvent>(
    initialState = MyPageState()
) {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

    // 로컬 데이터 저장소에서 사용자 정보를 가져오는 Flow
    private val _memberData: Flow<MemberData> = memberInfoRepository.memberData
    val memberData: Flow<MemberData> = _memberData

    override fun reduceState(currentState: MyPageState, event: MyPageEvent): MyPageState =
        when (event) {
            is MyPageEvent.RequestMyPageMemberData -> {
                currentState.copy(myPageMemberData = event.myPageMemberData)
            }

            is MyPageEvent.RequestMyPageNicknameDoubleCheck -> {
                currentState.copy(isNickNameUsable = event.isNickNameUsable)
            }

            is MyPageEvent.RequestMyPageSettingLoadData -> {
                currentState.copy(isAgreeAD = event.isAgreeAD, isAgreeAlarm = event.isAgreeAlarm)
            }

            is MyPageEvent.RequestMyPageNoticeData -> {
                currentState.copy(noticeArrayData = event.noticeArrayData)
            }

            is MyPageEvent.RequestMyPageEventData -> {
                currentState.copy(eventArrayData = event.eventArrayData)
            }

            is MyPageEvent.RequestMyPageFQAData -> {
                currentState.copy(fqaArrayData = event.fqaArrayData)
            }

            else -> currentState
        }

    fun requestMyPageMemberData(memberId: String) {
        setLoading(true)

        memberInfoRepository.requestMemberData(memberId)
            .onEach {
                Timber.i("$TAG requestMyPageMemberData: $it")
                sendAction(MyPageEvent.RequestMyPageMemberData(it.data))
            }
            .catch {
                Timber.e("$TAG $it")
            }
            .onCompletion {
                setLoading(false)
            }
            .launchIn(viewModelScope)
    }

    fun requestMyPageNicknameDoubleCheck(nickname: String) {
        setLoading(true)

        memberInfoRepository.requestNicknameDoubleCheck(nickname)
            .onEach {
                Timber.i("$TAG requestMyPageNicknameDoubleCheck: $it")
                if (it.data.usable) {
                    sendAction(MyPageEvent.RequestMyPageNicknameDoubleCheck(NickNameDoubleCheckData.USABLE))
                } else {
                    sendAction(MyPageEvent.RequestMyPageNicknameDoubleCheck(NickNameDoubleCheckData.UNUSABLE))
                }
            }
            .catch {
                Timber.e("$TAG $it")
            }
            .onCompletion {
                setLoading(false)
            }
            .launchIn(viewModelScope)
    }

    fun requestMyPageMemberSaveData(memberSaveData: RequestMyPageMemberSaveData) {
        setLoading(true)

        memberInfoRepository.requestMyPageMemberSaveData(memberSaveData)
            .onEach {
                Timber.i("$TAG requestMyPageMemberSaveData: $it ${memberSaveData.memberId}")
                delay(1000)
                // 성공 시 다시 사용자 정보를 업데이트 해줘야함.
                requestMyPageMemberData(memberSaveData.memberId)
            }
            .catch {
                Timber.e("$TAG $it")
            }
            .onCompletion {
                setLoading(false)
            }
            .launchIn(viewModelScope)
    }

    fun requestSettingLoadData(memberId: String) {
        memberInfoRepository.requestSettingLoadData(memberId)
            .onEach {
                Timber.i("$TAG requestSettingLoadData: $it")
                sendAction(MyPageEvent.RequestMyPageSettingLoadData(it.data.isAgreeAD, it.data.isAgreeAlarm))
            }
            .catch {
                Timber.e("$TAG $it")
            }
            .launchIn(viewModelScope)
    }

    fun requestSettingAlarm(memberId: String, isAgreeAlarm: Boolean) {
        memberInfoRepository.requestSettingAlarm(memberId, isAgreeAlarm)
            .onEach {
                Timber.i("$TAG requestSettingAlarm: $it")
            }
            .catch {
                Timber.e("$TAG $it")
            }
            .launchIn(viewModelScope)
    }

    fun requestSettingAD(memberId: String, isAgreeAD: Boolean) {
        memberInfoRepository.requestSettingAD(memberId, isAgreeAD)
            .onEach {
                Timber.i("$TAG requestSettingAD: $it")
            }
            .catch {
                Timber.e("$TAG $it")
            }
            .launchIn(viewModelScope)
    }

    suspend fun requestWithdrawalMember(
        withdrawalReason: String,
        withdrawalEtcData: String
    ) {
        val member = _memberData.first()
        val memberId = member.memberId
        Timber.i("$TAG requestWithdrawalMember: $memberId $withdrawalReason $withdrawalEtcData")

        setLoading(true)

        memberInfoRepository.requestWithdrawalMember(
            WithdrawalData(memberId, withdrawalReason, withdrawalEtcData)
        )
            .onEach {
                Timber.i("$TAG requestWithdrawalMember response: $it")
            }
            .catch { error ->
                Timber.e("$TAG Error: $error")
            }
            .onCompletion {
                setLoading(false)
            }
            .launchIn(viewModelScope)
    }

    fun requestMyPageNoticeData(myPageNoticeData: String) {
        setLoading(true)

        memberInfoRepository.requestMyPageNoticeData(myPageNoticeData)
            .onEach {
                Timber.i("$TAG requestMyPageNoticeData: $it")
                if (myPageNoticeData == "EVENT") {
                    sendAction(MyPageEvent.RequestMyPageEventData(it.data.items))
                } else if (myPageNoticeData == "FAQ") {
                    sendAction(MyPageEvent.RequestMyPageFQAData(it.data.items))
                } else {
                    sendAction(MyPageEvent.RequestMyPageNoticeData(it.data.items))
                }
            }
            .catch {
                Timber.e("$TAG $it")
            }
            .onCompletion {
                setLoading(false)
            }
            .launchIn(viewModelScope)
    }

    fun requestLogout() {
        setLoading(true)
        memberInfoRepository.requestLogout()
            .onEach {
                Timber.i("$TAG requestLogout: $it")
            }
            .catch {
                Timber.e("$TAG $it")
            }
            .onCompletion {
                setLoading(false)
            }
            .launchIn(viewModelScope)
    }

    fun requestWheatData(memberId: Int) {
//        setLoading(true)
//        memberInfoRepository.requestWheatData()
//            .onEach {
//                Timber.i("$TAG requestWheatData: $it")
//            }
//            .catch {
//                Timber.e("$TAG $it")
//            }
//            .onCompletion {
//                setLoading(false)
//            }
//            .launchIn(viewModelScope)
    }
}
