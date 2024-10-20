package com.desserttime.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.desserttime.design.R
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.Gallery
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.Tundora
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import timber.log.Timber

private const val TAG = "MyPageScreen::"
var globalMyPageUiState: MyPageState = MyPageState()

@Composable
fun MyPageScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToSetting: () -> Unit,
    onNavigateToMyInfo: () -> Unit,
    onNavigateToWheat: () -> Unit,
    onNavigateToNoticeAndEvent: () -> Unit,
    myPageViewModel: MyPageViewModel,
    onNavigateToQuestion: () -> Unit,
    onNavigationInquiryInput: () -> Unit,
    onNavigateToMyReview: () -> Unit
) {
    val myPageUiState by myPageViewModel.uiState.collectAsStateWithLifecycle()
    globalMyPageUiState = myPageUiState

    LaunchedEffect(myPageViewModel) {
        myPageViewModel.requestMyPageMemberData()
    }

    Timber.i("$TAG myPageUiState: ${globalMyPageUiState.myPageMemberData.nickName}, ${globalMyPageUiState.myPageMemberData.usersReviewCount}, ${globalMyPageUiState.myPageMemberData.usersTotalPoint}")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBarUi.AppBar(
                title = stringResource(id = R.string.txt_bottom_full_name_my_page),
                onSettingClick = { onNavigateToSetting() }
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
                    .background(WildSand)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                ProfileSection(
                    onNavigateToMyInfo,
                    onNavigateToWheat,
                    onNavigateToNoticeAndEvent,
                    myPageUiState,
                    onNavigateToQuestion,
                    onNavigationInquiryInput,
                    onNavigateToMyReview
                )
            }
        }
    )
}

@Composable
fun ProfileSection(
    onNavigateToMyInfo: () -> Unit,
    onNavigateToWheat: () -> Unit,
    onNavigateToNoticeAndEvent: () -> Unit,
    myPageUiState: MyPageState,
    onNavigateToQuestion: () -> Unit,
    onNavigationInquiryInput: () -> Unit,
    onNavigateToMyReview: () -> Unit
) {
    // NotLoginProfileSection( onNavigateToLogin() )
    // Spacer(modifier = Modifier.height(40.dp))
    // NoticeSection()

    LoginProfileSection(onNavigateToMyInfo)
    Spacer(modifier = Modifier.height(20.dp))
    MyReviewData(onNavigateToMyReview)
    Spacer(modifier = Modifier.height(4.dp))
    MyMileage(onNavigateToWheat)
    Spacer(modifier = Modifier.height(20.dp))
    NoticeSection(onNavigateToNoticeAndEvent, myPageUiState, onNavigateToQuestion, onNavigationInquiryInput)
}

// login 안 한 상태
@Composable
fun NotLoginProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 66.dp)
            .height(220.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_no_login),
            contentDescription = stringResource(id = R.string.img_mypage_profile),
            modifier = Modifier
                .size(106.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
        CenteredTextBox(
            text = stringResource(id = R.string.txt_mypage_no_login_title),
            textStyle = DessertTimeTheme.typography.textStyleRegular14,
            textColor = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton()
    }
}

// login 한 상태
@Composable
fun LoginProfileSection(onNavigateToMyInfo: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp)
            .height(220.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_like_profile),
            contentDescription = stringResource(id = R.string.img_mypage_profile),
            modifier = Modifier
                .size(106.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
        CenteredTextBox(
            text = globalMyPageUiState.myPageMemberData.nickName,
            textStyle = DessertTimeTheme.typography.textStyleBold24,
            textColor = Color.Black
        )
        ModifyMyInfo(onNavigateToMyInfo)
    }
}

@Composable
fun CenteredTextBox(
    text: String,
    textStyle: TextStyle,
    textColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = textStyle,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ModifyMyInfo(onNavigateToMyInfo: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .border(1.dp, Color.White, RoundedCornerShape(50))
                .padding(horizontal = 14.dp, vertical = 8.dp)
                .clickable { onNavigateToMyInfo() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.txt_mypage_edit_profile),
                color = Color.Black,
                style = DessertTimeTheme.typography.textStyleRegular12,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun MyReviewData(onNavigateToMyReview: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(WildSand)
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(
                    1.dp,
                    Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { onNavigateToMyReview() }
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight() // This will allow the height to adjust dynamically
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 17.dp,
                            horizontal = 20.dp
                        ), // Adjust padding for balance
                    verticalAlignment = Alignment.CenterVertically // Align content vertically in the center
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_mypage_behind),
                        color = Tundora,
                        style = DessertTimeTheme.typography.textStyleMedium16
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd // Aligns the Row to the end
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, // Ensure vertical alignment
                            horizontalArrangement = Arrangement.spacedBy(4.dp) // Add space between the Text and Image
                        ) {
                            Text(
                                text = globalMyPageUiState.myPageMemberData.usersReviewCount.toString(),
                                color = Color.Black,
                                style = DessertTimeTheme.typography.textStyleMedium16
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_right_arrow),
                                contentDescription = stringResource(id = R.string.txt_next),
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyMileage(
    onNavigateToWheat: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(WildSand)
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(
                    1.dp,
                    Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { onNavigateToWheat() }
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight() // This will allow the height to adjust dynamically
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 17.dp,
                            horizontal = 20.dp
                        ), // Adjust padding for balance
                    verticalAlignment = Alignment.CenterVertically // Align content vertically in the center
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_mypage_mileage),
                        color = Tundora,
                        style = DessertTimeTheme.typography.textStyleMedium16
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd // Aligns the Row to the end
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, // Ensure vertical alignment
                            horizontalArrangement = Arrangement.spacedBy(4.dp) // Add space between the Text and Image
                        ) {
                            Text(
                                text = globalMyPageUiState.myPageMemberData.usersTotalPoint.toString(),
                                color = Color.Black,
                                style = DessertTimeTheme.typography.textStyleMedium16
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_cash),
                                contentDescription = stringResource(id = R.string.txt_next),
                                modifier = Modifier
                                    .size(18.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_right_arrow),
                                contentDescription = stringResource(id = R.string.txt_next),
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoginButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(140.dp)
                .height(38.dp)
                .border(1.dp, MainColor, shape = RoundedCornerShape(50.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.txt_mypage_go_login),
                color = MainColor,
                style = DessertTimeTheme.typography.textStyleRegular16,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun NoticeSection(
    onNavigateToNoticeAndEvent: () -> Unit,
    myPageUiState: MyPageState,
    onNavigateToQuestion: () -> Unit,
    onNavigationInquiryInput: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(WildSand)
            .padding(horizontal = 24.dp)
            .border(1.dp, Gallery, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight() // Remove fixed height
                .background(Color.White) // Ensure background color is defined
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 15.dp,
                        horizontal = 20.dp
                    )
                    .clickable {
                        onNavigateToNoticeAndEvent()
                        myPageUiState.isNoticeAndEvent = true
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_notice),
                    contentDescription = stringResource(id = R.string.txt_mypage_notice),
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(16.dp)) // Better spacing between the image and text
                Text(
                    text = stringResource(id = R.string.txt_mypage_notice),
                    color = Tundora,
                    style = DessertTimeTheme.typography.textStyleMedium14
                )
            }
            Divider(
                color = Gallery,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 15.dp,
                        horizontal = 20.dp
                    )
                    .clickable {
                        onNavigateToNoticeAndEvent()
                        myPageUiState.isNoticeAndEvent = false
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_event),
                    contentDescription = stringResource(id = R.string.txt_mypage_event),
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(16.dp)) // Better spacing between the image and text
                Text(
                    text = stringResource(id = R.string.txt_mypage_event),
                    color = Tundora,
                    style = DessertTimeTheme.typography.textStyleMedium14
                )
            }
            Divider(
                color = Gallery,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 15.dp,
                        horizontal = 20.dp
                    )
                    .clickable { onNavigateToQuestion() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_question),
                    contentDescription = stringResource(id = R.string.txt_mypage_question),
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(16.dp)) // Better spacing between the image and text
                Text(
                    text = stringResource(id = R.string.txt_mypage_question),
                    color = Tundora,
                    style = DessertTimeTheme.typography.textStyleMedium14
                )
            }
            Divider(
                color = Gallery,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 15.dp,
                        horizontal = 20.dp
                    )
                    .clickable { onNavigationInquiryInput() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_inquire),
                    contentDescription = stringResource(id = R.string.txt_mypage_inquiry),
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(16.dp)) // Better spacing between the image and text
                Text(
                    text = stringResource(id = R.string.txt_mypage_inquiry),
                    color = Tundora,
                    style = DessertTimeTheme.typography.textStyleMedium14
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
    // MyPageScreen({}, {}, {}, {}, {}, myPageViewModel)
}
