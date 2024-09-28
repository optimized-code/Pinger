package io.features.home.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.features.home.domain.repository.UrlsRepo
import io.features.home.domain.usecase.UrlsUseCase

/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.features.home.domain.di
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */
 
@InstallIn(SingletonComponent::class)
@Module
object UrlsUseCaseModule {
    @Provides
    fun provideUrlsUseCase(urlsRepo: UrlsRepo): UrlsUseCase {
        return UrlsUseCase(urlsRepo)
    }
}