package com.desserttime.like

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.desserttime.design.R
import com.desserttime.design.ui.common.AppBarUi

@Composable
fun LikeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            // AppBar
            AppBarUi.AppBar(stringResource(id = R.string.txt_bottom_like))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LikeScreenPreview() {
    LikeScreen()
}
