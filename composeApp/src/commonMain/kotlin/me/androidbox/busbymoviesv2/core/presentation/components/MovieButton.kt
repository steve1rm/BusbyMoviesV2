package me.androidbox.busbymoviesv2.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.movie
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieButton(
    iconRes: DrawableResource,
    text: String,
    isLoading: Boolean = false,
    isEnabled: Boolean = false,
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    OutlinedButton(modifier = modifier
        .height(intrinsicSize = IntrinsicSize.Min),
        enabled = isEnabled,
        shape = RoundedCornerShape(8.dp),

        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.LightGray
        ),
        border = BorderStroke(1.dp, Color.Black),
        contentPadding = PaddingValues(0.dp),
        onClick = onClicked) {

        Icon(
            modifier = Modifier.size(24.dp).padding(start = 4.dp),
            painter = painterResource(iconRes),
            contentDescription = text,
            tint = Color.Black
        )

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 4.dp),
                strokeWidth = 2.dp,
                color = Color.Black
            )
        } else {
            Text(
                modifier = Modifier.wrapContentSize().padding(start = 4.dp, end = 4.dp),
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                text = text,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
    }
}
@Preview
@Composable
fun PreviewMovieButton() {
    MovieButton(
        iconRes = Res.drawable.movie,
        text = "Movies",
        onClicked = {}
    )
}