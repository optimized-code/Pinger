package io.core.network.data_providers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.core.network.ApiService
import io.core.network.dto.UrlsDto
import timber.log.Timber
import javax.inject.Inject

/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.core.network.data_providers
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

class UrlsDataProvider @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllUrls() = apiService.getAllUrls()
}