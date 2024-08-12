package com.desserttime.like

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.Mercury
import com.desserttime.design.ui.common.AppBarUi

@Composable
fun LikeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // AppBar
        AppBarUi.AppBar(
            stringResource(id = R.string.txt_bottom_like),
            {},
            {}
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Text(
            text = stringResource(id = R.string.txt_like_review),
            style = DessertTimeTheme.typography.textStyleBold18,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        LikeList()
    }
}

@Composable
fun LikeList() {
    LazyColumn {
        items(1) {
            LikeItem()
        }
    }
}

@Composable
fun LikeItem() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 8.dp, end = 8.dp)
            .border(1.dp, Mercury, shape = RoundedCornerShape(10.dp))
    ) {
        // row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 18.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_like_profile),
                contentDescription = stringResource(id = R.string.txt_like_nickname_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
            )
            // column
            // nickname
            // date
            // column
            // like button
            // like count
        }

        // title
        // score
        // picture
        // content
        // material
    }
}

@Preview(showBackground = true)
@Composable
fun LikeScreenPreview() {
    LikeScreen()
}
