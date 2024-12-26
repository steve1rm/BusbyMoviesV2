package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.favourite
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieTitleHeader(
    title: String,
    tagline: String,
    releaseDate: String,
    isFavourite: Boolean,
    modifier: Modifier = Modifier,
    onFavouriteClicked: () -> Unit
) {

    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .padding(top = 4.dp, bottom = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = tagline,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Thin
            )

            val transition = updateTransition(targetState = isFavourite, label = "Favourite Transition")

            /** Slowly transition in the color from red to white and white to red */
            val iconColor by transition.animateColor(
                transitionSpec = {
                    keyframes {
                        durationMillis = 1_000
                        if(isFavourite) {
                            Color.Red at 500
                        }
                        else {
                            Color.White at 1_000
                        }
                    }
                },
                label = "Color Animation",
                targetValueByState = { isFavourite ->
                    if (isFavourite) Color.Red else Color.White
                }
            )

            // Sequential size animation: Grow to 44.dp, then shrink back to 34.dp
            val iconSize by transition.animateDp(
                transitionSpec = {
                    keyframes {
                        durationMillis = 1000
                        44.dp at 500  // Peak at 44.dp halfway through the animation
                        34.dp at 1000 // Shrink back to 34.dp
                    }
                },
                label = "Size Animation",
                targetValueByState = { isFavourite ->
                    // Initial and final state sizes
                    34.dp
                }
            )

            Icon(
                modifier = Modifier
                    .size(iconSize)
                    .clickable(
                        onClick = {
                            onFavouriteClicked()
                        },
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ),
                painter = painterResource(resource = Res.drawable.favourite),
                contentDescription = "Favourite",
                tint = iconColor
            )
        }

        Divider(
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp),
            color = Color.LightGray,
            thickness = 1.dp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                modifier = Modifier.wrapContentWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = releaseDate,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
fun MovieTitleHeaderPreview() {
    MovieTitleHeader(
        title = "Seven Samurai",
        tagline = "The Mighty Warriors who became Heroes",
        releaseDate = "26 April 1964",
        modifier = Modifier,
        onFavouriteClicked = {},
        isFavourite = true)
}