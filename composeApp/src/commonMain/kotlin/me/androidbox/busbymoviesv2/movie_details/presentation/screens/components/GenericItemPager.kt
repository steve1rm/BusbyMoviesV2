@file:OptIn(ExperimentalFoundationApi::class)

package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> GenericItemPager(
    listOfItems: List<T>,
    content: @Composable (list: T) -> Unit,
    modifier: Modifier = Modifier
) {

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            /** Always plus 1 for the view all page as the final page */
            listOfItems.count()
        }
    )

    HorizontalPager(
        state = pagerState,
        pageSpacing = 16.dp,
        contentPadding = PaddingValues(end = 40.dp),
        modifier = modifier.wrapContentHeight()
    ) { pageIndex ->
        if(pageIndex <= listOfItems.count() - 1) {
            content(listOfItems[pageIndex])
        }
    }
}