package me.androidbox.busbymoviesv2.actors.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import me.androidbox.busbymoviesv2.actors.presentation.ActorState

@Composable
fun ActorsScreen(
    modifier: Modifier = Modifier,
    actorState: ActorState,
    lazyListState: LazyListState,
    shouldShowFab: Boolean,
    onScrollToTop: (scope: CoroutineScope) -> Unit,
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
            val scope = rememberCoroutineScope()

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                state = lazyListState
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

            if(shouldShowFab) {
                FloatingActionButton(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 16.dp, bottom = 16.dp)
                        .wrapContentSize(Alignment.BottomEnd),
                    onClick = {
                        onScrollToTop(scope)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Scroll to first position"
                    )
                }
            }
        }
    }
}