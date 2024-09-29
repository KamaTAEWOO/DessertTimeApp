package com.desserttime.mypage.question

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.domain.model.ContentDescriptionData

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
            QuestionItem(questionData[item].content, questionData[item].description)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun QuestionItem(
    content: String,
    description: String
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp)
            .clickable { isExpanded = !isExpanded } // Toggles expansion on click
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = content,
                    style = DessertTimeTheme.typography.textStyleRegular14,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = stringResource(id = R.string.txt_arrow_down),
                    modifier = Modifier
                        .size(16.dp)
                        .clickable { isExpanded = !isExpanded } // Toggles expansion on image click
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                style = DessertTimeTheme.typography.textStyleRegular12,
                color = Color.Gray,
                maxLines = if (isExpanded) Int.MAX_VALUE else 1, // Show one line or all lines
                overflow = TextOverflow.Ellipsis // Ellipsis for long text when collapsed
            )
        }
    }
}

@Composable
fun questionData(): List<ContentDescriptionData> {
    val contentDescriptionDataLists = listOf(
        ContentDescriptionData(
            content = stringResource(R.string.txt_question_test_title),
            description = stringResource(R.string.txt_question_test_content)
        ),
        ContentDescriptionData(
            content = stringResource(R.string.txt_question_test_title),
            description = stringResource(R.string.txt_question_test_content)
        )
    )
    return contentDescriptionDataLists
}
