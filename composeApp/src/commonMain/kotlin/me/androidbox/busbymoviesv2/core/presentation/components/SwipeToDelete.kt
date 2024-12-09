@file:OptIn(ExperimentalMaterialApi::class)

package me.androidbox.busbymoviesv2.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (item: T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (item: T) -> Unit
) {
    var isRemoved by remember { mutableStateOf(false) }
    val dismissState = rememberDismissState(
        confirmStateChange = { dimissedValue ->
            if(dimissedValue == DismissValue.DismissedToStart) {
                isRemoved = true
                true
            }
            else {
                false
            }
        }
    )

    LaunchedEffect(key1 = isRemoved) {
        if(isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut(),
        content = {
            SwipeToDismiss(
                state = dismissState,
                background = {
                    DeleteBackground(dismissState = dismissState)
                },
                dismissContent = {
                    content.invoke(item)
                },
                directions = setOf(DismissDirection.EndToStart))
        })

}

@Composable
fun DeleteBackground(
    dismissState: DismissState,
    modifier: Modifier = Modifier
) {
    val color = if(dismissState.dismissDirection == DismissDirection.EndToStart) {
        Color.Red
    }
    else {
        Color.Transparent
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete item",
            tint = Color.White)
    }
}































