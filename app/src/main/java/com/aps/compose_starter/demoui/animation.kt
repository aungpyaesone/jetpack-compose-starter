package com.aps.compose_starter.demoui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//
//val size by animateDpAsState(targetValue = sizeState,
//    animationSpec = tween(durationMillis = 3000, delayMillis = 2000, LinearOutSlowInEasing) )

//keyframes {
//    durationMillis = 5000
//    sizeState at 0 with LinearEasing
//    sizeState * 1.5f at 1000 with FastOutLinearInEasing
//    sizeState * 2f at 5000
//}


@Composable
fun CircularProgressBar(percentage:Float,
                        number: Int,
                        fontSize: TextUnit = 28.sp,
                        radius: Dp = 50.dp,
                        color: Color = Color.Green,
                        strokeWidth : Dp = 8.dp,
                        animDuration : Int = 1000,
                        animDelay: Int = 0){
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    var curPercentage = animateFloatAsState(targetValue =if(animationPlayed)
        percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true){
        animationPlayed = true
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)){
        Canvas(modifier = Modifier.size(radius*2f)){
            drawArc(color = color,
                -90f,
                360 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(),cap = StrokeCap.Round)
            )
        }
        Text(text = (curPercentage.value * number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

