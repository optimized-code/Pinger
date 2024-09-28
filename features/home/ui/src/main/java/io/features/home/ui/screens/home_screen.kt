package io.features.home.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.features.home.ui.screens
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {

//    Text(
//        text = "Hello an",
//        modifier = modifier
//    )

    /*
    steps
    1. initialize view model
    2. get all urls
    3. observe its result in lazyColumn
     */

    val result = viewModel.homeState
    Box(modifier = modifier
        .fillMaxSize()
        .background(color = Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        if (result.isLoading) {
            CircularProgressIndicator()
        }

        if (result.error.isNotEmpty()) {
            Text(result.error)
        }

        result.success.let { data ->
            val listState = rememberLazyListState()
            LazyColumn(
                modifier = modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                state = listState
            ){
                items(
                    items = data
                ) { url ->
                    Text(url.url)
                }
            }
        }
    }
}