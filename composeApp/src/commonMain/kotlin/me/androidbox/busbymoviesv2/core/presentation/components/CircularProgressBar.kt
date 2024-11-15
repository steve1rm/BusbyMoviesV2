package me.androidbox.busbymoviesv2.core.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    percentage: Float,
    number: Int = 100,
    fontSize: TextUnit = 12.sp,
    radius: Dp = 38.dp,
    strokeWidth: Dp = 2.dp,
    animationDuration: Int = 5_000,
    animationDelay: Int = 0) {

    var hasAnimationPlayed by remember { mutableStateOf(false) }
    val animateCurrentPercentage by animateFloatAsState(
        targetValue = if(hasAnimationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay,
            easing = LinearEasing
        )
    )

    LaunchedEffect(true) {
        hasAnimationPlayed = true
    }

    Box(
        modifier = modifier
            .size(radius)
            .background(color = Color.Black, shape = CircleShape).padding(4.dp),
        contentAlignment = Alignment.Center,
    ) {
        val targetColor = colorRanges.firstOrNull {
            (number * animateCurrentPercentage) in it.range
        }?.color ?: Color.Transparent

        val progressColor by animateColorAsState(
            targetValue = targetColor,
            animationSpec = tween(durationMillis = animationDuration / 2)
        )

        Canvas(
            modifier = Modifier.size(radius),
            onDraw = {
                drawArc(
                    color = Color.LightGray,
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round)
                )

                drawArc(
                    color = progressColor,
                    startAngle = -90f,
                    sweepAngle = 360 * animateCurrentPercentage,
                    useCenter = false,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round)
                )
            }
        )

        Text(text = (animateCurrentPercentage * number).toInt().toString(),
            color = Color.White,
            fontSize = fontSize,
            fontWeight = FontWeight.SemiBold)
    }
}

data class ColorRange(val range: ClosedFloatingPointRange<Float>, val color: Color)

private val colorRanges = listOf(
    ColorRange(0f..40f, Color(0xFFff0000)),  // Bad, don't waste your time
    ColorRange(40f..60f, Color(0xB39225FF)), // A little better, but not great
    ColorRange(60f..80f, Color(0xFFffff00)), // This is more like it
    ColorRange(80f..100f, Color(0xFF21d07a)) // Now that is what I am talking about
)