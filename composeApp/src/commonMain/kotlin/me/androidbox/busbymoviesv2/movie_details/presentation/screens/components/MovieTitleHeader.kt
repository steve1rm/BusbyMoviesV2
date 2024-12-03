package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.favourite
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieTitleHeader(
    title: String,
    tagline: String,
    releaseDate: String,
    hasSaved: Boolean,
    modifier: Modifier = Modifier,
    onFavouriteClicked: () -> Unit
) {
    var savedState by remember {
        mutableStateOf(false)
    }

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

            val transition = updateTransition(
                targetState = savedState
            )

            val iconColor by transition.animateColor(
                transitionSpec = {
                    tween(durationMillis = 500)
                },
                targetValueByState = {
                    if(savedState) Color.Red else Color.White
                })

            val iconSize by transition.animateDp(
                transitionSpec = {
                    tween(durationMillis = 500)
                },
                targetValueByState = {
                    if(savedState) 44.dp else 34.dp
                })

            Icon(
                modifier = Modifier
                    .size(iconSize)
                    .clickable(
                        onClick = {
                            onFavouriteClicked()
                            savedState = !savedState
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
        hasSaved = true)
}