package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieTitleHeader(
    modifier: Modifier = Modifier
) {
        Column(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .padding(top = 10.dp, bottom = 10.dp)
        ) {

            Text(
                text = "The Mighty Warriors who became Heroes",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Thin
            )

            Divider(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp),
                color = Color.LightGray,
                thickness = 1.dp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Seven Samurai",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "26 April 1954",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
}

@Preview
@Composable
fun MovieTitleHeaderPreview() {
    MovieTitleHeader(modifier = Modifier)
}