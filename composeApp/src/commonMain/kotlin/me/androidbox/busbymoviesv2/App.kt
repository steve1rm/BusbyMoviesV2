package me.androidbox.busbymoviesv2

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import me.androidbox.busbymoviesv2.move_list.presentation.MoveListViewModel
import me.androidbox.busbymoviesv2.move_list.presentation.screens.MovieListScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App() {

    val movieListViewModel : MoveListViewModel = koinViewModel()
    val movieListState = movieListViewModel.movieListState

    MaterialTheme {
        MovieListScreen(movieListState = movieListState)


/*
       var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }

            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
*/
    }
}