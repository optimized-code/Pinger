package io.core.network

import io.core.network.NetworkConstants.API_AUTH_TYPE
import io.core.network.NetworkConstants.API_PASSWORD
import io.core.network.NetworkConstants.API_USER_NAME
import io.core.network.NetworkConstants.NO_OF_LOG_CHAR
import io.core.network.NetworkConstants.TAG
import io.core.network.NetworkConstants.authKey
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

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

internal fun addHeadersInterceptor(chain: Interceptor.Chain): Response {
    val builder = chain.request().newBuilder()
        .addHeader("Content-Type", "application/json; charset=utf-8")
        .addHeader("authKey", "")
        .addHeader("apiAuthType", API_AUTH_TYPE)
    return chain.proceed(builder.build())
}

internal fun getHttpLoggingInterceptor(): Interceptor {
    val loggingInterceptor =
        HttpLoggingInterceptor { message ->
            if (message.length > NO_OF_LOG_CHAR) {
                for (noOfLogs in 0..message.length / NO_OF_LOG_CHAR) {
                    if (noOfLogs * NO_OF_LOG_CHAR + NO_OF_LOG_CHAR < message.length) {
                        Timber.d(
                            TAG,
                            message.substring(
                                noOfLogs * NO_OF_LOG_CHAR,
                                noOfLogs * NO_OF_LOG_CHAR + NO_OF_LOG_CHAR
                            )
                        )
                    } else {
                        Timber.d(
                            TAG,
                            message.substring(noOfLogs * NO_OF_LOG_CHAR, message.length)
                        )
                    }
                }
            } else {
                Timber.d(TAG, message)
            }
        }
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    //loggingInterceptor.redactHeader("Authorization")
    loggingInterceptor.redactHeader("Cookie")
    return loggingInterceptor
}

internal fun printPostmanStyleLogsInterceptor(chain: Interceptor.Chain): Response {

    var request = chain.request()
    printPostmanFormattedLog(request)

    val response = chain.proceed(request)
    Timber.d(TAG, "intercept: " + response.code)

    val token = response.header("token")
    if (response.code == 401 && !token.isNullOrEmpty()) {
        val usernamePasswordMd5 =
            getMd5String("$API_USER_NAME:$API_PASSWORD")
        authKey =
            getMd5String("$usernamePasswordMd5:$token")
        Timber.d(TAG, "token: $token")
        response.close()
        request =
            request.newBuilder().header("authKey", authKey)
                .build()
    }
    return chain.proceed(request)
}