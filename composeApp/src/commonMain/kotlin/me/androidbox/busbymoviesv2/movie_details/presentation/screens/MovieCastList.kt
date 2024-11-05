package me.androidbox.busbymoviesv2.movie_details.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.busbymoviesv2.movie_details.presentation.model.Credits

@Composable
fun MovieCastList(
    credits: Credits,
    isLoading: Boolean
) {
    Text(
        modifier = Modifier.padding(horizontal = 8.dp),
        text = "Top Billed Cast",
        color = Color.Black,
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,)

    Spacer(modifier = Modifier.height(4.dp))

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
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = credits.cast.take(9),
                key = { cast ->
                    cast.id
                }
            ) { cast ->
                CastItem(
                    cast = cast
                ) {
                    println("ID is $it")
                }
            }

            item {
                Button(
                    onClick = {}
                ) {
                    Text(text = "View More", fontSize = 22.sp, color = Color.Black)
                }
            }
        }
    }
}