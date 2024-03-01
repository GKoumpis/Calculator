package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalcButton(
    modifier: Modifier = Modifier,
    symbol: String,
    color: Color = Color.White,
    textColor: Color = Color.White,
    onClick: () -> Unit
){
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(80.dp))
            .background(color)
            .clickable {
                onClick()
            }
            .then(modifier),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = symbol,
            fontSize = 24.sp,
            color = textColor
        )
    }
}