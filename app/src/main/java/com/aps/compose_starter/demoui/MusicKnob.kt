package com.aps.compose_starter.demoui

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitingAngle : Float = 25f,
    onValueChange : (Float) -> Unit
){
    var rotation by remember {
        mutableStateOf(limitingAngle)
    }

    var touchX by remember {
        mutableStateOf(0f)
    }

    var touchY by remember {
        mutableStateOf(0f)
    }
    var centerX by remember {
        mutableStateOf(0f)
    }

    var centerY by remember {
        mutableStateOf(0f)
    }

    Image(painter = painterResource(id =com.aps.compose_starter.R.drawable.music_knob),
        contentDescription = "Music Knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val windowBound = it.boundsInWindow()
                centerX = windowBound.size.width / 2f
                centerY = windowBound.size.height / 2f
            }
            .pointerInteropFilter { motionEvent ->
                touchX = motionEvent.x
                touchY = motionEvent.y
                val angle = -atan2(centerX - touchX, centerY - touchY) * (180f / PI).toFloat()
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN,
                    MotionEvent.ACTION_MOVE -> {
                        if (angle !in -limitingAngle..limitingAngle) {
                            val fixedAngle = if (angle in -180f..-limitingAngle) {
                                360f + angle
                            } else {
                                angle
                            }
                            rotation = fixedAngle

                            val percent = (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
                            onValueChange(percent)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )

}

@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBars : Int = 0,
    barCount: Int = 10
){
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWith = remember {
            constraints.maxWidth / (2f * barCount)
        }
        Canvas(modifier = modifier){
            for (i in 0 until  barCount){
                drawRoundRect(
                    color = if(i in 0..activeBars) Color.Green else Color.Gray,
                    topLeft = Offset(i * barWith *2f + barCount,2f),
                    size = Size(barWith,constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }
    }
}

/*
setContent {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xff101010)),
        contentAlignment = Alignment.Center){
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(
                    1.dp, Color.Green,
                    RoundedCornerShape(10.dp)
                )
                .padding(30.dp),
        ) {
            var volume by remember {
                mutableStateOf(0f)
            }
            val barCount = 20
            com.aps.compose_starter.MusicKnob(modifier = Modifier.size(100.dp)){
                volume = it
            }
            Spacer(modifier = Modifier.width(20.dp))
            com.aps.compose_starter.VolumeBar(modifier = Modifier.fillMaxSize()
                .height(10.dp),
                activeBars = (barCount * volume).roundToInt(),
                barCount = barCount)
        }
    }
}*/
