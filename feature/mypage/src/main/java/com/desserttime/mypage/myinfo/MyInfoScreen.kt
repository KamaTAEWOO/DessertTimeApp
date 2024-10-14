package com.desserttime.mypage.myinfo

import android.annotation.SuppressLint
import android.net.Uri
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberImagePainter
import com.desserttime.design.R
import com.desserttime.design.theme.AzureRadiance
import com.desserttime.design.theme.Black
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.Gallery
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.MainColor20
import com.desserttime.design.theme.MineShaft
import com.desserttime.design.theme.SilverChalice
import com.desserttime.design.theme.Tundora
import com.desserttime.design.theme.Tundora60
import com.desserttime.design.theme.White
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.design.ui.common.CommonUi
import com.desserttime.design.ui.common.CommonUi.BirthYearDropdown
import com.desserttime.domain.model.GenderData
import com.desserttime.domain.model.MemberData
import com.desserttime.domain.model.NickNameDoubleCheckData
import com.desserttime.domain.model.RequestMyPageMemberSaveData
import com.desserttime.mypage.MyPageViewModel
import timber.log.Timber

private const val TAG = "MyInfoScreen::"

private var gBirthYear = ""
private var gGender = ""
private var gFirstCity = ""
private var gSecondCity = ""
private var gThirdCity = ""
private var gTaste = ""
private var gNickname = ""
private var gBackupMemberData: MemberData? = null

@Composable
fun MyInfoScreen(
    onNavigateToTasteChoose: () -> Unit,
    onBack: () -> Unit,
    myPageViewModel: MyPageViewModel
) {
    // ViewModel에 memberData?.memo에 저장
    val myPageUiState by myPageViewModel.uiState.collectAsStateWithLifecycle()

    val memberData = memberDataLoad()

    if (memberData == null) {
        Timber.i("$TAG memberData is null")
        return
    }

    var nickname by remember { mutableStateOf(TextFieldValue(memberData.nickName)) }
    var expanded by remember { mutableStateOf(false) }
    var selectedYear by remember { mutableStateOf(memberData.birthYear.toString() + "년") }
    var showAddressSearch by remember { mutableStateOf(false) }
    val selectedGenderData = remember {
        mutableStateOf<GenderData?>(
            when (memberData.gender) {
                "M" -> GenderData.MALE
                "F" -> GenderData.FEMALE
                else -> GenderData.OTHER
            }
        )
    }
    val selectAddress = remember { mutableStateOf(memberData.firstCity + " " + memberData.secondaryCity + " " + memberData.thirdCity) }
    val taste = remember { mutableStateOf(myPageUiState.taste.ifEmpty { memberData.memo ?: "" }) }
    val changeSaveColor = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            AppBarUi.AppBar(
                onBackClick = { onBack() },
                title = stringResource(id = R.string.txt_my_info_title),
                onSaveClick = { saveData(myPageViewModel) },
                color = if (changeSaveColor.value) AzureRadiance else Black30
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        top = paddingValues.calculateTopPadding(),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                        bottom = 0.dp // Remove the bottom padding
                    )
                    .background(Color.White)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                if (!showAddressSearch) {
                    OverlappingImages()
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 24.dp)
                ) {
                    if (!showAddressSearch) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = stringResource(id = R.string.txt_my_info_nickname),
                            style = DessertTimeTheme.typography.textStyleRegular16,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (!showAddressSearch) {
                            // Nickname Input Row with TextField and Button
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .border(
                                        1.dp, when (myPageUiState.isNickNameUsable) {
                                            NickNameDoubleCheckData.UNUSABLE -> MainColor
                                            NickNameDoubleCheckData.USABLE -> AzureRadiance
                                            else -> Gallery
                                        }, RoundedCornerShape(12.dp)
                                    )
                                    .padding(end = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CommonUi.CustomTextField(
                                    textFieldValue = nickname,
                                    onValueChange = { nickname = it },
                                    placeholderText = stringResource(id = R.string.txt_my_info_nickname_hint),
                                    placeholderStyle = DessertTimeTheme.typography.textStyleMedium16,
                                    containerColor = Color.Transparent,
                                    cursorColor = Color.Black,
                                    focusedIndicatorColor = Color.White,
                                    unfocusedIndicatorColor = Color.White,
                                    textStyle = DessertTimeTheme.typography.textStyleMedium16,
                                    underlineThickness = 1.dp,
                                    paddingVertical = 0.dp,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp) // Adjust TextField padding
                                )

                                Button(
                                    onClick = { nicknameDoubleCheck(myPageViewModel, nickname.text) },
                                    modifier = Modifier
                                        .size(82.dp, 40.dp)
                                        .align(Alignment.CenterVertically),
                                    colors = ButtonDefaults.buttonColors(if (nickname.text.isEmpty()) WildSand else MainColor),
                                    shape = RoundedCornerShape(6.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.txt_my_info_nickname_check),
                                        color = if (nickname.text.isEmpty()) Tundora60 else Color.White,
                                        style = DessertTimeTheme.typography.textStyleMedium14,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )
                                }
                            }

                            // 이미 존재하는 닉네임입니다.
                            when (myPageUiState.isNickNameUsable) {
                                NickNameDoubleCheckData.UNUSABLE -> {
                                    Timber.i("$TAG NickNameDoubleCheckData.UNUSABLE")
                                    Spacer(modifier = Modifier.height(4.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(), // 가로로 꽉 차게 설정
                                        horizontalArrangement = Arrangement.End // 오른쪽 끝으로 정렬
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.txt_my_info_nickname_error),
                                            style = DessertTimeTheme.typography.textStyleRegular14,
                                            color = MainColor
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                }
                                NickNameDoubleCheckData.USABLE -> {
                                    Timber.i("$TAG NickNameDoubleCheckData.USABLE")
                                    Spacer(modifier = Modifier.height(4.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(), // 가로로 꽉 차게 설정
                                        horizontalArrangement = Arrangement.End // 오른쪽 끝으로 정렬
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.txt_my_info_nickname_access),
                                            style = DessertTimeTheme.typography.textStyleRegular14,
                                            color = AzureRadiance
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                }
                                else -> {
                                    Timber.i("$TAG NickNameDoubleCheckData.NONE")
                                    Spacer(modifier = Modifier.height(20.dp))
                                }
                            }

                            // Gender Selection
                            Text(
                                text = stringResource(id = R.string.txt_sex),
                                style = DessertTimeTheme.typography.textStyleRegular16,
                                color = MineShaft,
                                modifier = Modifier.align(Alignment.Start)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(
                                    onClick = { selectedGenderData.value = GenderData.MALE },
                                    colors = ButtonDefaults.buttonColors(
                                        if (selectedGenderData.value == GenderData.MALE) MainColor20 else WildSand
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                ) {
                                    Text(
                                        text = stringResource(R.string.txt_sex_man),
                                        color = if (selectedGenderData.value == GenderData.MALE) MainColor else Tundora,
                                        style = DessertTimeTheme.typography.textStyleRegular16
                                    )
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                Button(
                                    onClick = { selectedGenderData.value = GenderData.FEMALE },
                                    colors = ButtonDefaults.buttonColors(
                                        if (selectedGenderData.value == GenderData.FEMALE) MainColor20 else WildSand
                                    ),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                ) {
                                    Text(
                                        text = stringResource(R.string.txt_sex_woman),
                                        color = if (selectedGenderData.value == GenderData.FEMALE) MainColor else Tundora,
                                        style = DessertTimeTheme.typography.textStyleRegular16
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            // Birth Date Selection
                            Text(
                                text = stringResource(id = R.string.txt_birth),
                                style = DessertTimeTheme.typography.textStyleRegular16,
                                color = MineShaft,
                                modifier = Modifier.align(Alignment.Start)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = { expanded = true }, // 버튼 클릭 시 DropdownMenu를 표시
                                colors = ButtonDefaults.buttonColors(White),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .border(1.dp, Gallery, RoundedCornerShape(12.dp))
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = selectedYear.ifEmpty { stringResource(R.string.txt_birth_hint) }, // 조건에 따라 hint 또는 선택된 연도 표시
                                        color = if (selectedYear.isEmpty()) Black30 else Black, // 힌트일 때와 선택된 값일 때 색상 다르게
                                        style = DessertTimeTheme.typography.textStyleRegular16
                                    )

                                    Image(
                                        painter = painterResource(R.drawable.ic_calendar),
                                        contentDescription = stringResource(R.string.txt_birth_description),
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }

                            // DropdownMenu 표시
                            BirthYearDropdown(
                                expanded = expanded,
                                onYearSelected = { year ->
                                    selectedYear = year + "년" // 선택된 연도 설정
                                    expanded = false // DropdownMenu 닫기
                                },
                                selectedYear = selectedYear,
                                onDismiss = {
                                    expanded = false // DropdownMenu를 닫기 위한 콜백
                                }
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            // Address Selection
                            Text(
                                text = stringResource(id = R.string.txt_address),
                                style = DessertTimeTheme.typography.textStyleRegular16,
                                color = MineShaft,
                                modifier = Modifier.align(Alignment.Start)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = { showAddressSearch = true },
                                colors = ButtonDefaults.buttonColors(White),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .border(1.dp, Gallery, RoundedCornerShape(12.dp))
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = selectAddress.value.ifEmpty { stringResource(R.string.txt_address_hint) },
                                        color = if (selectAddress.value.isEmpty()) Black30 else Black,
                                        style = DessertTimeTheme.typography.textStyleRegular16
                                    )

                                    Image(
                                        painter = painterResource(R.drawable.ic_search),
                                        contentDescription = stringResource(R.string.txt_address_description),
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = stringResource(id = R.string.txt_my_info_taste),
                                style = DessertTimeTheme.typography.textStyleRegular16,
                                color = MineShaft,
                                modifier = Modifier.align(Alignment.Start)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = { onNavigateToTasteChoose() },
                                colors = ButtonDefaults.buttonColors(White),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .border(1.dp, Gallery, RoundedCornerShape(12.dp))
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = (taste.value.ifEmpty { stringResource(R.string.txt_my_info_taste_hint) }),
                                        color = if (taste.value.isEmpty()) Black30 else Black,
                                        style = DessertTimeTheme.typography.textStyleRegular16,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.weight(1f) // 텍스트가 가능한 한 많은 공간을 차지하도록 설정
                                    )
                                    Spacer(modifier = Modifier.width(8.dp)) // 이미지 왼쪽에 빈 공간 추가
                                    Image(
                                        painter = painterResource(R.drawable.ic_right_arrow),
                                        contentDescription = stringResource(R.string.txt_my_info_taste_hint),
                                        modifier = Modifier.size(30.dp),
                                        colorFilter = ColorFilter.tint(SilverChalice)
                                    )
                                }
                            }
                        }
                        if (showAddressSearch) {
                            AddressSearchView(onAddressSelected = { address ->
                                selectAddress.value = address
                                showAddressSearch = false
                                Timber.d("$TAG Address $address")
                            })
                        }
                    }
                }
            }
        }
    )

    Timber.i("$TAG Data: $nickname, $selectedYear, $selectedGenderData, $selectAddress, $taste")
    val changeButtonColor = inputData(nickname, selectedYear, selectedGenderData, selectAddress, taste)
    changeSaveColor.value = changeButtonColor
}

@Composable
fun OverlappingImages() {
    val currentImageUri = remember { mutableStateOf<Uri?>(null) }
    // 이미지 선택을 위한 ActivityResultLauncher 설정
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            currentImageUri.value = uri
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        ) {
            if (currentImageUri.value != null) {
                Image(
                    painter = rememberImagePainter(currentImageUri.value),
                    contentDescription = stringResource(id = R.string.txt_my_info_nickname),
                    modifier = Modifier
                        .size(106.dp)
                        .clip(RoundedCornerShape(53.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                // 기본 이미지가 없을 경우 보여줄 기본 이미지
                Image(
                    painter = painterResource(id = R.drawable.ic_like_profile),
                    contentDescription = stringResource(id = R.string.txt_my_info_nickname),
                    modifier = Modifier
                        .size(106.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_modify_nickname),
                contentDescription = stringResource(id = R.string.txt_my_info_nickname),
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.BottomEnd)
                    .clickable {
                        imagePickerLauncher.launch("image/*")
                    }
            )
        }
    }
}

@Composable
fun memberDataLoad(): MemberData? {
    // ViewModel 가져오기
    val myPageViewModel: MyPageViewModel = hiltViewModel()

    // MemberData 가져오기
    val memberData by myPageViewModel.memberData.collectAsState(initial = null)

    if (memberData == null) {
        Timber.i("$TAG memberDataLoad is null")
    }

    // Handle the MemberData when it's available
    LaunchedEffect(memberData) {
        memberData?.let {
            Timber.i("$TAG memberDataLoad: $it")
        }
    }

    gBackupMemberData = memberData

    return memberData
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AddressSearchView(onAddressSelected: (String) -> Unit) {
    var webView by remember { mutableStateOf<WebView?>(null) }
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            // Add a refresh button to reload the page
            Button(
                onClick = { webView?.reload() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Refresh Page")
            }

            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        webChromeClient = object : WebChromeClient() {
                            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                super.onProgressChanged(view, newProgress)
                                Timber.i("$TAG webChromeClient", "Loading progress: $newProgress%")
                            }
                        }

                        webViewClient = object : WebViewClient() {
                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                Timber.i("$TAG webViewClient", "Page loaded: $url")
                            }

                            override fun onReceivedError(
                                view: WebView?,
                                errorCode: Int,
                                description: String?,
                                failingUrl: String?
                            ) {
                                super.onReceivedError(view, errorCode, description, failingUrl)
                                Timber.e("$TAG WebViewError", "Error: $description")
                            }
                        }

                        settings.apply {
                            javaScriptEnabled = true
                            domStorageEnabled = true
                            javaScriptCanOpenWindowsAutomatically = true
                        }

                        WebView.setWebContentsDebuggingEnabled(true)

                        // JavaScript Interface for communication
                        addJavascriptInterface(
                            JavascriptBridge { address ->
                                onAddressSelected(address)
                            },
                            "Android"
                        )

                        // Load the desired URL
                        loadUrl("https://dessert-time-44a86.web.app")
                    }
                },
                update = { newWebView ->
                    webView = newWebView
                },
                modifier = Modifier.weight(1f) // Take up remaining space
            )
        }
    }
}

private fun nicknameDoubleCheck(myPageViewModel: MyPageViewModel, nickname: String) {
    // Check if the nickname is already in use
    myPageViewModel.requestMyPageNicknameDoubleCheck(nickname)
}

private fun inputData(
    nickname: TextFieldValue,
    selectedYear: String,
    selectedGenderData: MutableState<GenderData?>,
    selectAddress: MutableState<String>,
    taste: MutableState<String>
) : Boolean {
    // gBackupMemberData가 null이면 return
    if (gBackupMemberData == null) {
        Timber.i("$TAG gBackupMemberData is null")
        return false
    }

    // 닉네임 비교
    if (gBackupMemberData?.nickName == nickname.text) {
        Timber.i("$TAG gBackupMemberData?.nickName == nickname.text 같음")
    } else {
        Timber.i("$TAG gBackupMemberData?.nickName == nickname.text 다름")
    }

    // 출생년도 비교
    val currentYear = selectedYear.substring(0, selectedYear.length - 1).toInt()
    if (gBackupMemberData?.birthYear == currentYear) {
        Timber.i("$TAG 출생년도 같음")
    } else {
        Timber.i("$TAG 출생년도 다름")
    }

    // 성별 비교
    var currentGender = selectedGenderData.value?.name
    currentGender = when (currentGender) {
        "MALE" -> "M"
        "FALEMA" -> "F"
        else -> "O"
    }

    if (gBackupMemberData?.gender == currentGender) {
        Timber.i("$TAG 성별 같음")
    } else {
        Timber.i("$TAG 성별 다름")
    }

    // 주소 비교 (첫 번째, 두 번째, 세 번째 주소)
    val addressParts = selectAddress.value.split(" ")
    val currentFirstCity = addressParts.getOrNull(0) ?: ""
    val currentSecondCity = addressParts.getOrNull(1) ?: ""
    val currentThirdCity = addressParts.getOrNull(2) ?: ""

    if (gBackupMemberData?.firstCity == currentFirstCity &&
        gBackupMemberData?.secondaryCity == currentSecondCity &&
        gBackupMemberData?.thirdCity == currentThirdCity) {
        Timber.i("$TAG 주소 같음")
    } else {
        Timber.i("$TAG 주소 다름")
    }

    // 취향 비교
//    if (gBackupMemberData?.taste == taste.value) {
//        Timber.i("$TAG 취향 같음")
//    } else {
//        Timber.i("$TAG 취향 다름")
//    }

    // 여기서 버튼 색상 업데이트 (데이터가 다를 경우)
    val isDataChanged = gBackupMemberData?.let { backup ->
        backup.nickName != nickname.text || backup.birthYear != currentYear || backup.gender != currentGender || backup.firstCity != currentFirstCity || backup.secondaryCity != currentSecondCity || backup.thirdCity != currentThirdCity
    } ?: false

    // id
    // 출생년도
    gBirthYear = selectedYear.substring(0, selectedYear.length - 1)
    // 성별
    gGender = when (selectedGenderData.value) {
        GenderData.MALE -> "M"
        GenderData.FEMALE -> "F"
        else -> "O"
    }
    // 첫 주소
    gFirstCity = currentFirstCity
    // 두번째 주소
    gSecondCity = currentSecondCity
    // 세번째 주소
    gThirdCity = currentThirdCity
    // 닉네임
    gNickname = nickname.text
    // 취향
    gTaste = taste.value

    return isDataChanged
}

private fun saveData(myPageViewModel: MyPageViewModel) {
    // Save the data to the server
    Timber.i("$TAG saveData: $gBirthYear, $gGender, $gFirstCity, $gSecondCity, $gThirdCity, $gNickname, $gTaste")
    val memberSaveData = RequestMyPageMemberSaveData(
        memberId = "1",
        birth = gBirthYear,
        gender = gGender,
        firstCity = gFirstCity,
        secondCity = gSecondCity,
        thirdCity = gThirdCity,
        nickName = gNickname
    )
    myPageViewModel.requestMyPageMemberSaveData(memberSaveData)
}

class JavascriptBridge(val onAddressSelected: (String) -> Unit) {
    @JavascriptInterface
    fun processDATA(address: String) {
        onAddressSelected(address)
    }
}
