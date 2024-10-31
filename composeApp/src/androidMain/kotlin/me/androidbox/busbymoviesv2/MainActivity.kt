package me.androidbox.busbymoviesv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
        //    CircularProgressBar(percentage = 0.8f, number = 100)
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}