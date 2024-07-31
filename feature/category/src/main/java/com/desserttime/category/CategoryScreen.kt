package com.desserttime.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.desserttime.design.R
import com.desserttime.design.ui.common.AppBarUi

@Composable
fun CategoryScreen() {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        AppBarUi.AppBar(stringResource(id = R.string.txt_bottom_category))
        Text("Category Screen", modifier = Modifier.align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    CategoryScreen()
}
