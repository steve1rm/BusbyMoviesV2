package me.androidbox.busbymoviesv2.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    Button(modifier = modifier
        .wrapContentWidth()
        .padding(horizontal = 6.dp)
        .padding(vertical = 4.dp)
        .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
        onClick = onClicked) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier.wrapContentSize(),
                painter = painterResource(iconRes),
                contentDescription = text
            )

            Text(
                modifier = Modifier.wrapContentSize(),
                fontSize = 16.sp,
                textAlign = TextAlign.End,
                text = text
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