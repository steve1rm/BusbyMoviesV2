package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CrewItem(
    modifier: Modifier = Modifier,
    name: String,
    job: String
) {

    Column(modifier = modifier) {
        Text(text = name, fontWeight = FontWeight.Medium, color = Color.Black, fontSize = 14.sp)
        Text(text = job, color = Color.Black, fontSize = 14.sp)
    }
}