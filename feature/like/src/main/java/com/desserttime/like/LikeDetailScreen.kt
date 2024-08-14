package com.desserttime.like

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.desserttime.design.R
import com.desserttime.design.ui.common.AppBarUi

@Composable
fun LikeDetailScreen(
    onNavigateToLike: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // AppBar
        AppBarUi.AppBar(
            {},
            stringResource(id = R.string.txt_like_detail_title),
            {},
            {}
        )
    }
}