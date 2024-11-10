package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> MovieAnyItemList(
    items: List<T>,
    itemContent: @Composable (T) -> Unit,
    viewMoreContent: @Composable () -> Unit,
    isLoading: Boolean
) {
    if(isLoading) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    else {
        LazyRow(
            state = rememberLazyListState(),
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(
                items = items.take(9),
                key = { item ->
                    println("item.hashCode ${item.hashCode()}")
                    item.hashCode()
                }
            ) { item ->
                itemContent(item)
            }

            item {
                viewMoreContent()
            }
        }
    }
}