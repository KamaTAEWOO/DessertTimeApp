package com.desserttime.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.theme.Athens_Gray
import com.desserttime.design.theme.Flamingo
import com.desserttime.design.ui.common.CommonUi

@Composable
fun SignUpInputScreen(
    onNavigateToSignUpChoose: () -> Unit,
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
                .border(1.dp, Athens_Gray, RoundedCornerShape(10.dp)) // Use Color.Gray if Athens_Gray is not defined
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Athens_Gray, RoundedCornerShape(10.dp)) // Use Color.Gray if Athens_Gray is not defined
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f) // Width is 50% of the parent
                    .fillMaxHeight() // Full height
                    .background(Flamingo, RoundedCornerShape(10.dp)) // Use Color.Red if Flamingo is not defined
            )
        }
        Spacer(Modifier.padding(top = 28.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpInputScreen() {
    SignUpInputScreen({}, {})
}