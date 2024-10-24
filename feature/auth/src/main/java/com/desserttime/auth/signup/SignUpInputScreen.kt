package com.desserttime.auth.signup

import android.annotation.SuppressLint
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.desserttime.auth.AuthViewModel
import com.desserttime.design.R
import com.desserttime.design.theme.AthensGray
import com.desserttime.design.theme.Black
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.Black60
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.Flamingo
import com.desserttime.design.theme.Gallery
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.MainColor20
import com.desserttime.design.theme.MineShaft
import com.desserttime.design.theme.Tundora
import com.desserttime.design.theme.White
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.CommonUi
import com.desserttime.domain.model.GenderData
import timber.log.Timber

private const val TAG = "SignUpInputScreen"

@Composable
fun SignUpInputScreen(
    onNavigateToSignUpChoose: () -> Unit,
    onBack: () -> Unit,
    authViewModel: AuthViewModel
) {
    val selectedGenderData = remember { mutableStateOf<GenderData?>(GenderData.OTHER) }
    var selectedBirth by remember { mutableStateOf("1997") }
    val selectedAddress = remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var showAddressSearch by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showAddressSearch) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Spacer(Modifier.padding(top = 66.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp) // Adjust height to ensure visibility
                        .border(
                            1.dp,
                            AthensGray,
                            RoundedCornerShape(10.dp)
                        ) // Use Color.Gray if Athens_Gray is not defined
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                AthensGray,
                                RoundedCornerShape(10.dp)
                            ) // Use Color.Gray if Athens_Gray is not defined
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f) // Width is 50% of the parent
                            .fillMaxHeight() // Full height
                            .background(
                                Flamingo,
                                RoundedCornerShape(10.dp)
                            ) // Use Color.Red if Flamingo is not defined
                    )
                }
                Spacer(Modifier.padding(top = 28.dp))
                Text(
                    text = stringResource(id = R.string.txt_add_input),
                    style = DessertTimeTheme.typography.textStyleBold26,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.Start)
                )
                Spacer(Modifier.padding(top = 6.dp))
                Text(
                    text = stringResource(id = R.string.txt_add_description),
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    color = Black60,
                    modifier = Modifier
                        .align(Alignment.Start)
                )
                Spacer(Modifier.padding(top = 36.dp))
                Text(
                    text = stringResource(id = R.string.txt_sex),
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    color = MineShaft,
                    modifier = Modifier
                        .align(Alignment.Start)
                )
                Spacer(Modifier.padding(top = 8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        // 남성 버튼
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

                        // 여성 버튼
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
                }
                Spacer(Modifier.padding(top = 20.dp))
                Text(
                    text = stringResource(id = R.string.txt_birth),
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    color = MineShaft,
                    modifier = Modifier
                        .align(Alignment.Start)
                )
                Spacer(Modifier.padding(top = 8.dp))
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
                            text = selectedBirth.ifEmpty { stringResource(R.string.txt_birth_hint) }, // 조건에 따라 hint 또는 선택된 연도 표시
                            color = if (selectedBirth.isEmpty()) Black30 else Black, // 힌트일 때와 선택된 값일 때 색상 다르게
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
                CommonUi.BirthYearDropdown(
                    expanded = expanded,
                    onYearSelected = { year ->
                        selectedBirth = year // 선택된 연도 설정
                        expanded = false // DropdownMenu 닫기
                    },
                    selectedYear = selectedBirth,
                    onDismiss = {
                        expanded = false // DropdownMenu를 닫기 위한 콜백
                    }
                )
                Spacer(Modifier.padding(top = 20.dp))
                Text(
                    text = stringResource(id = R.string.txt_address),
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    color = MineShaft,
                    modifier = Modifier
                        .align(Alignment.Start)
                )
                Spacer(Modifier.padding(top = 8.dp))
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
                        horizontalArrangement = Arrangement.SpaceBetween // 텍스트와 이미지를 양 끝에 배치
                    ) {
                        Text(
                            text = selectedAddress.value.ifEmpty { stringResource(R.string.txt_address_hint) },
                            color = Black30,
                            style = DessertTimeTheme.typography.textStyleRegular16
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_search), // 이미지 리소스를 사용
                            contentDescription = stringResource(R.string.txt_address_description),
                            modifier = Modifier.size(24.dp) // 이미지 크기 조정
                        )
                    }
                }
            }
        }
        if (showAddressSearch) {
            AddressSearchView(onAddressSelected = { address ->
                selectedAddress.value = address
                showAddressSearch = false
                Timber.d("$TAG Address $address")
            })
        }
        if (!showAddressSearch) {
            Spacer(Modifier.padding(top = 188.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 58.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                CommonUi.NextButton(
                    text = stringResource(R.string.txt_next),
                    onClick = {
                        saveSignUpInputData(
                            authViewModel,
                            onNavigateToSignUpChoose,
                            selectedGenderData.value,
                            selectedBirth,
                            selectedAddress.value
                        )
                    },
                    background = MainColor20,
                    textColor = MainColor,
                    enabled = true
                )
            }
        }
    }
}

private fun saveSignUpInputData(
    authViewModel: AuthViewModel,
    onNavigateToSignUpChoose: () -> Unit,
    sex: GenderData?,
    birth: String,
    address: String
) {
    authViewModel.saveMemberGenderData(if (sex == GenderData.MALE) "M" else "F")
    authViewModel.saveBirthYearData(birth.toInt())
    // address는 띄워쓰기에 따라 3개로 나누어 저장
    // 주소를 공백으로 분리
    val addressList = address.split(" ")

    if (addressList.size >= 3) {
        authViewModel.saveFirstCityData(addressList[0])
        authViewModel.saveSecondaryCityData(addressList[1])

        val thirdCityData = addressList.drop(2).joinToString(" ")
        authViewModel.saveThirdCityData(thirdCityData)
    } else {
        // 요소가 3개 미만일 경우 처리
        Timber.e("주소 리스트의 요소가 부족합니다: ${addressList.size}")
    }

    onNavigateToSignUpChoose()
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

class JavascriptBridge(val onAddressSelected: (String) -> Unit) {
    @JavascriptInterface
    fun processDATA(address: String) {
        onAddressSelected(address)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpInputScreen() {
//    SignUpInputScreen({}, {}, AuthViewModel())
}
