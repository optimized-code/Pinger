package io.core.network

import io.core.network.NetworkConstants.CONNECT_TIMEOUT_MULTIPLIER
import io.core.network.NetworkConstants.DEFAULT_CONNECT_TIMEOUT_IN_SEC
import io.core.network.NetworkConstants.DEFAULT_READ_TIMEOUT_IN_SEC
import io.core.network.NetworkConstants.DEFAULT_WRITE_TIMEOUT_IN_SEC
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.core.network
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

internal class RetrofitClient {
    private val sDispatcher: Dispatcher? = null

    fun getOkHttpClientBuilder(): OkHttpClient.Builder {
        /*OkHttp client builder*/
        val oktHttpClientBuilder = OkHttpClient.Builder().followRedirects(true)
            .connectTimeout(
                (CONNECT_TIMEOUT_MULTIPLIER * DEFAULT_CONNECT_TIMEOUT_IN_SEC).toLong(),
                TimeUnit.SECONDS
            )
            .writeTimeout(
                (CONNECT_TIMEOUT_MULTIPLIER * DEFAULT_WRITE_TIMEOUT_IN_SEC).toLong(),
                TimeUnit.SECONDS
            )
            .readTimeout(
                (CONNECT_TIMEOUT_MULTIPLIER * DEFAULT_READ_TIMEOUT_IN_SEC).toLong(),
                TimeUnit.SECONDS
            )

        oktHttpClientBuilder.dispatcher(getDispatcher())
        oktHttpClientBuilder.addInterceptor { addHeadersInterceptor(it) }
        oktHttpClientBuilder.addInterceptor(getHttpLoggingInterceptor())
        oktHttpClientBuilder.addInterceptor { printPostmanStyleLogsInterceptor(it) }

        return oktHttpClientBuilder
    }

    private fun getDispatcher(): Dispatcher {
        return sDispatcher ?: Dispatcher()
    }
}