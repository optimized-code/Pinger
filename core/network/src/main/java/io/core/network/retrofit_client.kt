package io.core.network

import android.util.Log
import io.core.network.NetworkConstants.API_AUTH_TYPE
import io.core.network.NetworkConstants.API_PASSWORD
import io.core.network.NetworkConstants.API_USER_NAME
import io.core.network.NetworkConstants.CONNECT_TIMEOUT_MULTIPLIER
import io.core.network.NetworkConstants.DEFAULT_CONNECT_TIMEOUT_IN_SEC
import io.core.network.NetworkConstants.DEFAULT_READ_TIMEOUT_IN_SEC
import io.core.network.NetworkConstants.DEFAULT_WRITE_TIMEOUT_IN_SEC
import io.core.network.NetworkConstants.TAG
import io.core.network.NetworkConstants.authKey
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import timber.log.Timber
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

//    fun getOkHttpClientBuilder(): OkHttpClient.Builder {
//        /*OkHttp client builder*/
//        val oktHttpClientBuilder = OkHttpClient.Builder().followRedirects(true)
//            .connectTimeout(
//                (CONNECT_TIMEOUT_MULTIPLIER * DEFAULT_CONNECT_TIMEOUT_IN_SEC).toLong(),
//                TimeUnit.SECONDS
//            )
//            .writeTimeout(
//                (CONNECT_TIMEOUT_MULTIPLIER * DEFAULT_WRITE_TIMEOUT_IN_SEC).toLong(),
//                TimeUnit.SECONDS
//            )
//            .readTimeout(
//                (CONNECT_TIMEOUT_MULTIPLIER * DEFAULT_READ_TIMEOUT_IN_SEC).toLong(),
//                TimeUnit.SECONDS
//            )
//
//        oktHttpClientBuilder.dispatcher(getDispatcher())
//        //oktHttpClientBuilder.addInterceptor { addHeadersInterceptor(it) }
//        oktHttpClientBuilder.addInterceptor(getHttpLoggingInterceptor())
//        oktHttpClientBuilder.addInterceptor { printPostmanStyleLogsInterceptor(it) }
//
//        return oktHttpClientBuilder
//    }

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
        oktHttpClientBuilder.addInterceptor { chain ->
            val builder = chain.request().newBuilder()
                //.addHeader("Content-Type", "text/html")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("authKey", "")
                .addHeader("apiAuthType", API_AUTH_TYPE)
//                .addHeader("token", AuthKeyHelper.getInstance().token.toString())
            //.addHeader("Authorization", GlobalAppSharedPref.customerBearerToken ?: "")
            //.addHeader("Authorization", ApplicationConstants.ADMIN_TOKEN)

            chain.proceed(builder.build())
        }

        oktHttpClientBuilder.addInterceptor(getHttpLoggingInterceptor())
        oktHttpClientBuilder.addInterceptor { chain ->
            var request = chain.request()

            printPostmanFormattedLog(request)

            var response = chain.proceed(request)
            Timber.d(TAG, "intercept: " + response.code)
            val token = response.header("token")
            if (response.code == 401 && token != null && token.isNotEmpty()) {
                val usernamePasswordMd5 =
                    getMd5String("$API_USER_NAME:$API_PASSWORD")
                authKey =
                    getMd5String("$usernamePasswordMd5:$token")
                Timber.d(TAG, "token: $token")
                response.close()
                request =
                    request.newBuilder().header("authKey", authKey)
                        .build()
                response = chain.proceed(request)
            }
            response
        }
        return oktHttpClientBuilder
    }

    private fun getDispatcher(): Dispatcher {
        return sDispatcher ?: Dispatcher()
    }
}