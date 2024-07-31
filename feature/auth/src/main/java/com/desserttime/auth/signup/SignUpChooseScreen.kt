package com.desserttime.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.AthensGray
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.DoveGray
import com.desserttime.design.theme.Flamingo
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.MainColor20
import com.desserttime.design.theme.Silver
import com.desserttime.design.ui.common.CommonUi

@Composable
fun SignUpChooseScreen(
    onNavigateToSignUpComplete: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 26.dp, end = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
                        Flamingo,
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
            text = stringResource(id = R.string.txt_choose_interest),
            style = DessertTimeTheme.typography.textStyleBold26,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.padding(top = 6.dp))
        Text(
            text = stringResource(id = R.string.txt_choose_interest_hint),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = DoveGray,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
    // 취향 리스트
    Column {
        Spacer(Modifier.padding(top = 194.dp))
        ViewSelectTasteData()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 58.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        CommonUi.NextButton(
            text = stringResource(R.string.txt_next),
            onClick = onNavigateToSignUpComplete,
            background = MainColor20,
            textColor = MainColor
        )
    }
}

@Composable
private fun ViewSelectTasteData() {
    val items = listOf(
        R.drawable.ic_fish_shaped_bun_off to stringResource(id = R.string.txt_fish_shaped_bun),
        R.drawable.ic_baked_confectionery_off to stringResource(id = R.string.txt_baked_confectionery),
        R.drawable.ic_donut_off to stringResource(id = R.string.txt_donut),
        R.drawable.ic_rice_cake_off to stringResource(id = R.string.txt_rice_cake),
        R.drawable.ic_macaron_off to stringResource(id = R.string.txt_macaron),
        R.drawable.ic_bakery_off to stringResource(id = R.string.txt_bakery),
        R.drawable.ic_fruit_dessert_off to stringResource(id = R.string.txt_fruit_dessert),
        R.drawable.ic_summer_off to stringResource(id = R.string.txt_summer_dessert),
        R.drawable.ic_yogert_off to stringResource(id = R.string.txt_yogurt),
        R.drawable.ic_tradition_dessert_off to stringResource(id = R.string.txt_tradition_dessert),
        R.drawable.ic_chocolate_off to stringResource(id = R.string.txt_chocolate),
        R.drawable.ic_cake_off to stringResource(id = R.string.txt_cake),
        R.drawable.ic_cookie_off to stringResource(id = R.string.txt_cookie),
        R.drawable.ic_tart_off to stringResource(id = R.string.txt_tart),
        R.drawable.ic_pan_cake_off to stringResource(id = R.string.txt_pan_cake),
        R.drawable.ic_pastry_off to stringResource(id = R.string.txt_pastries),
        R.drawable.ic_pudding_off to stringResource(id = R.string.txt_pudding)
    )

    SelectTasteRecyclerView(items = items)
}

@Composable
fun SelectTasteRecyclerView(items: List<Pair<Int, String>>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp)
    ) {
        items(items.size) { index ->
            val item = items[index]
            GridItem(imageRes = item.first, text = item.second)
        }
    }
}

@Composable
fun GridItem(imageRes: Int, text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp, bottom = 10.dp) // Add spacing to each item
            .size(72.dp)
            .clip(CircleShape) // Clip to a circular shape
            .border(1.dp, AthensGray, CircleShape) // Add a circular border
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = text,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp)
        )
        Text(
            text = text,
            style = DessertTimeTheme.typography.textStyleRegular12,
            color = Silver,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpChooseScreen() {
    SignUpChooseScreen({}, {})
}
