package me.androidbox.busbymoviesv2.actors.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.person
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ActorItem(
    modifier: Modifier = Modifier,
    id: Int,
    imagePath: String,
    name: String,
    character: String,
    onActorClicked: (actorId: Int) -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable {
                onActorClicked(id)
            },
        elevation = 4.dp) {

        Row(
            modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterHorizontally
        )
        ) {
            KamelImage(
                modifier = Modifier
                    .size(80.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                resource = { asyncPainterResource(data = imagePath) },
                contentDescription = name,
                contentAlignment = Alignment.Center,
                onLoading = {
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
                }
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    text = character,
                    fontSize = 14.sp
                )
            }
        }
    }
}

