package io.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.core.network.ApiService
import io.core.network.data_providers.UrlsDataProvider


/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.core.network.di
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@InstallIn(SingletonComponent::class)
@Module
object UrlsDataProviderModule {
    @Provides
    fun providesUrlsDataProvider(apiService: ApiService): UrlsDataProvider {
        return UrlsDataProvider(apiService)
    }
}