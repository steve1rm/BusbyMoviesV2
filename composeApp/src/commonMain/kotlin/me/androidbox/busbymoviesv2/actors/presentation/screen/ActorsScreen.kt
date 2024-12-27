package me.androidbox.busbymoviesv2.actors.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.androidbox.busbymoviesv2.actors.presentation.ActorState

@Composable
fun ActorsScreen(
    modifier: Modifier = Modifier,
    actorState: ActorState,
    onClickActor: (actorId: Int) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(actorState.isLoading) {
            CircularProgressIndicator()
        }
        else {
            val state = rememberLazyListState()

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                state = state
            ) {
                items(
                    items = actorState.actors,
                    key = { castModel ->
                        castModel.id
                    },
                    itemContent = { castModel ->
                        ActorItem(
                            imagePath = castModel.profilePath,
                            name = castModel.name,
                            character = castModel.character,
                            id = castModel.id,
                            onActorClicked = { actorId ->
                                onClickActor(actorId)
                            }
                        )
                    }
                )
            }
        }
    }
}