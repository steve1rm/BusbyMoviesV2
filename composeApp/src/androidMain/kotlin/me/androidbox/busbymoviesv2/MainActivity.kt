package me.androidbox.busbymoviesv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {

                CircularProgressBar(
                    0.83f,
                    100
                )
            }
        }
    }
}



@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 12.sp,
    radius: Dp = 32.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 4.dp,
    animationDuration: Int = 2_000,
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
        modifier = Modifier.size(radius),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier.size(radius)) {

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * animateCurrentPercentage,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round)
            )
        }

        Text(text = (animateCurrentPercentage * number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.SemiBold)
    }

}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}