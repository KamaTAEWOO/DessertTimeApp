package com.desserttime.mypage.question

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.domain.model.ContentDateData

@Composable
fun QuestionScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            AppBarUi.AppBar(
                {},
                title = stringResource(id = R.string.txt_question_title)
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
                Spacer(Modifier.height(20.dp))
                QuestionContent()
            }
        }
    )
}

@Composable
fun QuestionContent() {
    val questionData = questionData()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        items(questionData.size) { item ->
            QuestionItem(questionData[item].content, questionData[item].date)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun QuestionItem(
    content: String,
    date: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(73.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = content,
                style = DessertTimeTheme.typography.textStyleRegular14,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = date,
                style = DessertTimeTheme.typography.textStyleRegular12,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun questionData(): List<ContentDateData> {
    val contentDateDataLists = listOf(
        ContentDateData(content = stringResource(R.string.txt_question_test_title), date = stringResource(R.string.txt_question_test_content)),
        ContentDateData(content = stringResource(R.string.txt_question_test_title), date = stringResource(R.string.txt_question_test_content))
    )
    return contentDateDataLists
}