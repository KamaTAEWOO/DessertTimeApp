package com.desserttime.auth.signup

import android.annotation.SuppressLint
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import java.util.Calendar

@Composable
fun SignUpInputScreen(
    onNavigateToSignUpChoose: () -> Unit,
    authViewModel: AuthViewModel
) {
    val selectedGenderData = remember { mutableStateOf<GenderData?>(GenderData.OTHER) }
    var selectedBirth by remember { mutableStateOf("") }
    var selectedAddress by remember { mutableStateOf("") }
    var selectedAddressClick by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.padding(top = 66.dp))
        ProgressBar()
        Spacer(Modifier.padding(top = 28.dp))
        TitleSection()
        Spacer(Modifier.padding(top = 36.dp))
        GenderSelection(
            selectedGenderData = selectedGenderData.value,
            onGenderSelected = { selectedGenderData.value = it }
        )
        Spacer(Modifier.padding(top = 20.dp))
        BirthSelection(
            selectedBirth = selectedBirth,
            onBirthSelected = { selectedBirth = it }
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
            onClick = { selectedAddressClick = true },
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
                    text = selectedAddress.ifEmpty { stringResource(R.string.txt_address_hint) },
                    color = if (selectedAddress.isEmpty()) Black30 else Black,
                    style = DessertTimeTheme.typography.textStyleRegular16
                )
                Image(
                    painter = painterResource(R.drawable.ic_search), // 이미지 리소스를 사용
                    contentDescription = stringResource(R.string.txt_address_description),
                    modifier = Modifier.size(24.dp) // 이미지 크기 조정
                )
            }
        }
        InputNextButton(
            authViewModel,
            onNavigateToSignUpChoose,
            selectedGenderData,
            selectedBirth,
            selectedAddress
        )
    }

    // 주소 선택 화면
    if (selectedAddressClick) {
        AddressSelection {
            selectedAddress = it
            selectedAddressClick = false
        }
    }
}

@Composable
private fun ProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(5.dp)
            .border(1.dp, AthensGray, RoundedCornerShape(10.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AthensGray, RoundedCornerShape(10.dp))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight()
                .background(Flamingo, RoundedCornerShape(10.dp))
        )
    }
}

@Composable
private fun TitleSection() {
    Column(
        modifier = Modifier.fillMaxWidth(), // Ensures the Column takes full width
        horizontalAlignment = Alignment.Start // Aligns children to the start
    ) {
        Text(
            text = stringResource(id = R.string.txt_add_input),
            style = DessertTimeTheme.typography.textStyleBold26,
            color = Color.Black
        )
        Spacer(Modifier.height(6.dp)) // Properly space using height instead of padding
        Text(
            text = stringResource(id = R.string.txt_add_description),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = Black60
        )
    }
}

@Composable
private fun GenderSelection(
    selectedGenderData: GenderData?,
    onGenderSelected: (GenderData) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.txt_sex),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = MineShaft,
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
    Spacer(Modifier.padding(top = 8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GenderButton(
            text = stringResource(R.string.txt_sex_man),
            isSelected = selectedGenderData == GenderData.MALE,
            onClick = { onGenderSelected(GenderData.MALE) },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        GenderButton(
            text = stringResource(R.string.txt_sex_woman),
            isSelected = selectedGenderData == GenderData.FEMALE,
            onClick = { onGenderSelected(GenderData.FEMALE) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun GenderButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            if (isSelected) MainColor20 else WildSand
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.fillMaxHeight()
    ) {
        Text(
            text = text,
            color = if (isSelected) MainColor else Tundora,
            style = DessertTimeTheme.typography.textStyleRegular16
        )
    }
}

@Composable
private fun BirthSelection(
    selectedBirth: String,
    onBirthSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    Column {
        Text(
            text = stringResource(id = R.string.txt_birth),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = MineShaft,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(Modifier.padding(top = 8.dp))
        Button(
            onClick = { expanded = true },
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
                    text = if (selectedBirth.isNotEmpty()) "$selectedBirth 년" else stringResource(R.string.txt_birth_hint),
                    color = if (selectedBirth.isEmpty()) Black30 else Black,
                    style = DessertTimeTheme.typography.textStyleRegular16
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = stringResource(R.string.txt_birth_description),
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // 드롭다운 메뉴 표시
        CommonUi.BirthYearDropdown(
            expanded = expanded,
            onYearSelected = { year ->
                onBirthSelected(year)
                expanded = false
            },
            currentYear = currentYear,
            onDismiss = {
                expanded = false
            }
        )
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
    }

    onNavigateToSignUpChoose()
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AddressSelection(onAddressSelected: (String) -> Unit) {
    var webView by remember { mutableStateOf<WebView?>(null) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        webChromeClient = object : WebChromeClient() {
                            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                super.onProgressChanged(view, newProgress)
                                // Update progress bar or UI state if needed
                            }
                        }

                        webViewClient = object : WebViewClient() {
                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                            }

                            override fun onReceivedError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                error: WebResourceError?
                            ) {
                                super.onReceivedError(view, request, error)
                                // Display an error message or fallback UI
                            }
                        }

                        settings.apply {
                            javaScriptEnabled = true
                            domStorageEnabled = true
                            javaScriptCanOpenWindowsAutomatically = true
                        }

                        WebView.setWebContentsDebuggingEnabled(true)

                        addJavascriptInterface(
                            JavascriptBridge { address ->
                                if (address.isNotBlank()) {
                                    onAddressSelected(address)
                                }
                            },
                            "Android"
                        )

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

    // Clean up resources when the Composable is removed
    DisposableEffect(Unit) {
        onDispose {
            webView?.destroy()
            webView = null
        }
    }
}

class JavascriptBridge(private val onAddressSelected: (String) -> Unit) {
    @JavascriptInterface
    fun processDATA(address: String) {
        onAddressSelected(address)
    }
}

@Composable
private fun InputNextButton(
    authViewModel: AuthViewModel,
    onNavigateToSignUpChoose: () -> Unit,
    selectedGenderData: MutableState<GenderData?>,
    selectedBirth: String,
    selectedAddress: String
) {
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
                    selectedAddress
                )
            },
            background = if (selectedBirth.isEmpty() || selectedAddress.isEmpty()) MainColor20 else MainColor,
            textColor = if (selectedBirth.isEmpty() || selectedAddress.isEmpty()) MainColor else Color.White,
            enabled = true
        )
    }
}
