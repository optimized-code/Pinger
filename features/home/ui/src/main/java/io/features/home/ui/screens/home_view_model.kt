package io.features.home.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.core.common.UiEvents
import io.features.home.domain.usecase.UrlsUseCase
import io.features.home.ui.helpers.HomeStateHolder
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

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

@HiltViewModel
class HomeViewModel @Inject constructor(private val urlsUseCase: UrlsUseCase): ViewModel() {

    var homeState by mutableStateOf(HomeStateHolder())
        private set

    init {
        getAllUrls()
    }

    private fun getAllUrls() = viewModelScope.launch {
        urlsUseCase.invoke().onEach {
            homeState = when(it){
                is UiEvents.Loading -> { HomeStateHolder(isLoading = true)  }
                is UiEvents.Success -> { HomeStateHolder(success = it.data ?: emptyList()) }
                is UiEvents.Error -> { HomeStateHolder(error = it.message) }
            }
        }.launchIn(viewModelScope)
    }
}