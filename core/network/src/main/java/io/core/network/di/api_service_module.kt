package io.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.core.network.ApiService
import io.core.network.Endpoints
import io.core.network.RetrofitClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Named

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
object ApiServiceModule {
    @Provides
    @Named("apiClient")
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create())
            .baseUrl(Endpoints.BASE_URL)
            .client(RetrofitClient().getOkHttpClientBuilder().build())
            .build()
            .create(ApiService::class.java)
    }
}