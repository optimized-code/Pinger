package io.features.home.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.core.network.data_providers.UrlsDataProvider
import io.features.home.data.repository.UrlsRepoImpl
import io.features.home.domain.repository.UrlsRepo
import javax.inject.Singleton

/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.features.home.data.di
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@InstallIn(SingletonComponent::class)
@Module
object UrlsRepoModule {
    @Provides
    fun provideUrlsRepo(urlsDataProvider: UrlsDataProvider): UrlsRepo {
        return UrlsRepoImpl(urlsDataProvider)
    }
}