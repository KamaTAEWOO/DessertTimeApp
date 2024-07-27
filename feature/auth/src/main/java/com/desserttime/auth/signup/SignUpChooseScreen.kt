package com.desserttime.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.desserttime.design.R
import com.desserttime.design.theme.AthensGray
import com.desserttime.design.theme.Flamingo

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
    }

    val items = listOf(
        R.drawable.ic_fish_shaped_bun_on to "Text 1",
//        R.drawable.image2 to "Text 2",
//        R.drawable.image3 to "Text 3",
//        R.drawable.image4 to "Text 4",
    )

    //SelectTasteRecyclerView(items = items)
}

@Composable
fun SelectTasteRecyclerView(items: List<Pair<Int, String>>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
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
            .padding(4.dp)
            .size(72.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = text,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .padding(4.dp)
        )
        Text(
            text = text,
            fontSize = 12.sp,
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