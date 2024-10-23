package com.desserttime.mypage

import androidx.lifecycle.viewModelScope
import com.desserttime.core.base.BaseViewModel
import com.desserttime.domain.model.MemberData
import com.desserttime.domain.model.NickNameDoubleCheckData
import com.desserttime.domain.model.RequestMyPageMemberSaveData
import com.desserttime.domain.model.WithdrawalData
import com.desserttime.domain.repository.MemberInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
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

            else -> currentState
        }

    fun requestMyPageMemberData() {
        memberInfoRepository.requestMemberData()
            .onEach {
                Timber.i("$TAG requestMyPageMemberData: $it")
                sendAction(MyPageEvent.RequestMyPageMemberData(it.data))
            }
            .catch {
                Timber.e("$TAG $it")
            }
            .launchIn(viewModelScope)
    }

    fun requestMyPageNicknameDoubleCheck(nickname: String) {
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
            .launchIn(viewModelScope)
    }

    fun requestMyPageMemberSaveData(memberSaveData: RequestMyPageMemberSaveData) {
        memberInfoRepository.requestMyPageMemberSaveData(memberSaveData)
            .onEach {
                Timber.i("$TAG requestMyPageMemberSaveData: $it")
            }
            .catch {
                Timber.e("$TAG $it")
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

        // Call the repository to request withdrawal
        memberInfoRepository.requestWithdrawalMember(
            WithdrawalData(memberId, withdrawalReason, withdrawalEtcData)
        )
            .onEach {
                Timber.i("$TAG requestWithdrawalMember response: $it")
            }
            .catch { error ->
                Timber.e("$TAG Error: $error")
            }
            .launchIn(viewModelScope)
    }

    fun requestMyPageNoticeData(myPageNoticeData: Boolean) {
        memberInfoRepository.requestMyPageNoticeData(myPageNoticeData)
            .onEach {
                Timber.i("$TAG requestMyPageNoticeData: $it")
                it.data?.let { it1 -> MyPageEvent.RequestMyPageNoticeData(it1) }
                    ?.let { it2 -> sendAction(it2) }
            }
            .catch {
                Timber.e("$TAG $it")
            }
            .launchIn(viewModelScope)
    }
}
