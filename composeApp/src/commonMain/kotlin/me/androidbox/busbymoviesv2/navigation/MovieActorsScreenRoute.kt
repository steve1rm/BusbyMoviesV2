package me.androidbox.busbymoviesv2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.launch
import me.androidbox.busbymoviesv2.actors.presentation.ActorsViewModel
import me.androidbox.busbymoviesv2.actors.presentation.screen.ActorsScreen
import org.koin.compose.viewmodel.koinViewModel

data class MovieActorsScreenRoute(val movieId: Int) : Screen {

    @Composable
    override fun Content() {
        val actorsViewModel = koinViewModel<ActorsViewModel>()
        val actorsState by actorsViewModel.actorState.collectAsStateWithLifecycle()
        val shouldShowFab by actorsViewModel.shouldShowFab.collectAsStateWithLifecycle()

        actorsViewModel.saveMovieId(movieId)

        ActorsScreen(
            actorState = actorsState,
            lazyListState = actorsViewModel.lazyListState,
            onClickActor = { actorId ->
                println(actorId)
            },
            onScrollToTop = { scope ->
                scope.launch {
                    actorsViewModel.lazyListState.animateScrollToItem(0)
                }
            },
            shouldShowFab = shouldShowFab
        )
    }
}