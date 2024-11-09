package me.androidbox.busbymoviesv2.movie_details.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.person
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import me.androidbox.busbymoviesv2.movie_details.presentation.model.Cast
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Composable
fun CastItem(
    cast: Cast,
    modifier: Modifier = Modifier,
    onCastClicked: (id: Int) -> Unit
) {
    var maxWidth by remember {
        mutableIntStateOf(0)
    }
    
    val maxWidthDp = with(LocalDensity.current) {
        maxWidth.toDp()
    }

    var maxheight by remember {
        mutableIntStateOf(0)
    }

    val maxHeightDp = with(LocalDensity.current) {
        maxheight.toDp()
    }
    
    Card(
        modifier = modifier
            .fillMaxHeight()
            .clickable {
                onCastClicked(cast.id)
            },
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(top = 4.dp, bottom = 10.dp, start = 6.dp, end = 6.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            KamelImage(
                resource = { asyncPainterResource(data = cast.profilePath) },
                contentDescription = cast.name,
                modifier = Modifier
                    .aspectRatio(16f/9f)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop,
                contentAlignment = Alignment.Center,
                onLoading = { _ ->
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Blue
                    )
                },
                onFailure = {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(Res.drawable.person),
                        contentDescription = "Fall back image"
                    )
                    it.printStackTrace()
                },
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = cast.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = cast.character,
                fontSize = 14.sp
            )
        }
    }
}

fun listOfCastItems(): List<Cast> {
    return generateSequence<Cast> {
        Cast(
            id = Random.nextInt(),
            name = "John Doe",
            character = "Character",
            popularity = 10.0,
            creditId = "001",
            castId = 130,
            profilePath = "https://image.tmdb.org/t/p/w500/kU3B75TyRiCgE270EyZnHjfivoq.jpg"
        )
    }.take(10).toList()
}

@Preview
@Composable
fun CastItemPreview() {
    CastItem(
        cast = Cast(
            id = 1,
            name = "John Doe",
            character = "Character",
            popularity = 10.0,
            creditId = "001",
            castId = 130,
            profilePath = "https://image.tmdb.org/t/p/w500/kU3B75TyRiCgE270EyZnHjfivoq.jpg"
        ),
        onCastClicked = {}
    )
}