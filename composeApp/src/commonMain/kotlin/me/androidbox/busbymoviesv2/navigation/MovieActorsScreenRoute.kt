package me.androidbox.busbymoviesv2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.busbymoviesv2.actors.presentation.ActorsViewModel
import me.androidbox.busbymoviesv2.actors.presentation.screen.ActorsScreen
import org.koin.compose.viewmodel.koinViewModel

class MovieActorsScreenRoute : Screen {

    @Composable
    override fun Content() {
        val actorsViewModel = koinViewModel<ActorsViewModel>()
        val actorsState by actorsViewModel.actorState.collectAsStateWithLifecycle()

        ActorsScreen(
            actorState = actorsState,
            onClickActor = { actorId ->
                println(actorId)
            }
        )
    }
}