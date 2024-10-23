package com.desserttime.mypage.withdrawal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.desserttime.design.R
import com.desserttime.design.theme.Alto
import com.desserttime.design.theme.AzureRadiance
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.Black60
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.Tundora
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.design.ui.common.CommonUi
import com.desserttime.mypage.MyPageViewModel
import kotlinx.coroutines.launch

private var withdrawalReason: String = ""
private var withdrawalEtcData: String = ""

@Composable
fun WithdrawalScreen(
    onNavigateToWithdrawalComplete: () -> Unit,
    onBack: () -> Unit,
    myPageViewModel: MyPageViewModel
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            AppBarUi.AppBar(
                { onBack() },
                title = stringResource(id = R.string.txt_withdrawal_title)
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
                WithdrawalContent()
            }
        },
        bottomBar = {
            WithdrawalBottomScreen(
                onNavigateToWithdrawalComplete,
                onBack,
                myPageViewModel
            )
        }
    )
}

@Composable
fun WithdrawalContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        WithdrawalDescription()
        WithdrawalReason()
    }
}

@Composable
fun WithdrawalDescription() {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.txt_withdrawal_sub_title1),
            color = Color.Black,
            style = DessertTimeTheme.typography.textStyleMedium18
        )
        Spacer(modifier = Modifier.height(11.dp))
        Text(
            text = stringResource(id = R.string.txt_withdrawal_description),
            color = Black60,
            style = DessertTimeTheme.typography.textStyleRegular12
        )
        Spacer(modifier = Modifier.height(20.dp))
        CommonUi.Divide(color = Alto)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WithdrawalReason() {
    var contentText by remember { mutableStateOf("") }

    Column {
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = stringResource(id = R.string.txt_withdrawal_sub_title2),
            color = Color.Black,
            style = DessertTimeTheme.typography.textStyleMedium18
        )
        AllWithdrawalRadioButtonGroup()
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = contentText,
            onValueChange = { newText -> contentText = newText },
            textStyle = DessertTimeTheme.typography.textStyleRegular16,
            modifier = Modifier
                .fillMaxWidth()
                .height(108.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.txt_inquiry_content_hint),
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    color = Black30
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = WildSand,
                focusedIndicatorColor = AzureRadiance,
                unfocusedIndicatorColor = Black30
            )
        )
        withdrawalEtcData = contentText
    }
}

@Composable
fun WithdrawalBottomScreen(
    onNavigateToWithdrawalComplete: () -> Unit,
    onBack: () -> Unit,
    myPageViewModel: MyPageViewModel
) {
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp, start = 28.dp, end = 28.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(61.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    withdrawalComplete(
                        onNavigateToWithdrawalComplete,
                        myPageViewModel
                    )
                },
                colors = ButtonDefaults.buttonColors(WildSand),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(WildSand, RoundedCornerShape(12.dp))
            ) {
                Text(
                    text = stringResource(R.string.txt_withdrawal_title),
                    color = Tundora,
                    style = DessertTimeTheme.typography.textStyleMedium20
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { onBack() },
                colors = ButtonDefaults.buttonColors(MainColor),
                modifier = Modifier
                    .weight(1.4f)
                    .fillMaxHeight()
                    .background(MainColor, RoundedCornerShape(12.dp))
            ) {
                Text(
                    text = stringResource(R.string.txt_cancel),
                    color = Color.White,
                    style = DessertTimeTheme.typography.textStyleMedium20
                )
            }
        }
    }
}

@Composable
fun AllWithdrawalRadioButtonGroup() {
    val options = listOf(
        stringResource(R.string.txt_withdrawal_sub_description1),
        stringResource(R.string.txt_withdrawal_sub_description2),
        stringResource(R.string.txt_withdrawal_sub_description3),
        stringResource(R.string.txt_withdrawal_sub_description4)
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(options[0]) }

    Column {
        options.forEachIndexed { _, text ->
            Spacer(Modifier.padding(top = 24.dp))
            Row {
                RadioButton(
                    modifier = Modifier
                        .size(24.dp, 24.dp),
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .wrapContentSize()
                )
                withdrawalReason = selectedOption
            }
        }
    }
}

fun withdrawalComplete(
    onNavigateToWithdrawalComplete: () -> Unit,
    myPageViewModel: MyPageViewModel
) {
    if (withdrawalEtcData.isEmpty()) {
        withdrawalEtcData = "X"
    }

    if (withdrawalReason.isEmpty()) {
        withdrawalReason = "X"
    }

    myPageViewModel.viewModelScope.launch {
        myPageViewModel.requestWithdrawalMember(
            withdrawalReason,
            withdrawalEtcData
        )
        onNavigateToWithdrawalComplete()
    }
}
