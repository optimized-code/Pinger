package io.core.network

import android.util.Log
import io.core.network.NetworkConstants.API_AUTH_TYPE
import io.core.network.NetworkConstants.API_PASSWORD
import io.core.network.NetworkConstants.API_USER_NAME
import io.core.network.NetworkConstants.CONNECT_TIMEOUT_MULTIPLIER
import io.core.network.NetworkConstants.DEFAULT_CONNECT_TIMEOUT_IN_SEC
import io.core.network.NetworkConstants.DEFAULT_READ_TIMEOUT_IN_SEC
import io.core.network.NetworkConstants.DEFAULT_WRITE_TIMEOUT_IN_SEC
import io.core.network.NetworkConstants.NO_OF_LOG_CHAR
import io.core.network.NetworkConstants.TAG
import io.core.network.NetworkConstants.authKey
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import timber.log.Timber
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.nio.charset.Charset
import java.security.NoSuchAlgorithmException
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
            Log.d(TAG, "intercept: " + response.code)
            val token = response.header("token")
            if (response.code == 401 && token != null && token.isNotEmpty()) {
                val usernamePasswordMd5 =
                    getMd5String("$API_USER_NAME:$API_PASSWORD")
                authKey =
                    getMd5String("$usernamePasswordMd5:$token")
                Log.d(TAG, "token: $token")
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

    private fun getMd5String(stringToConvert: String): String {
        try {
            // Create MD5 Hash
            val digest = java.security.MessageDigest
                .getInstance(API_AUTH_TYPE)
//                        .getInstance("MD5")
            digest.update(stringToConvert.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    private fun printPostmanFormattedLog(request: Request) {
        try {
            val allParams: String = if (request.method == "GET" || request.method == "DELETE") {
                request.url.toString().substring(
                    request.url.toString().indexOf("?") + 1,
                    request.url.toString().length
                )
            } else {
                val buffer = Buffer()
                request.body!!.writeTo(buffer)
                buffer.readString(Charset.forName("UTF-8"))
            }
            val params =
                allParams.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val paramsString = StringBuilder("\n")
            for (param in params) {
                paramsString.append(decode(param.replace("=", ":")))
                paramsString.append("\n")
            }
            Log.d(TAG, paramsString.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun decode(url: String): String {
        return try {
            var prevURL = ""
            var decodeURL = url
            while (prevURL != decodeURL) {
                prevURL = decodeURL
                decodeURL = URLDecoder.decode(decodeURL, "UTF-8")
            }
            decodeURL
        } catch (e: UnsupportedEncodingException) {
            "Issue while decoding" + e.message
        }
    }

    private fun getHttpLoggingInterceptor(): Interceptor {
        val loggingInterceptor =
            HttpLoggingInterceptor { message ->
                if (message.length > NO_OF_LOG_CHAR) {
                    for (noOfLogs in 0..message.length / NO_OF_LOG_CHAR) {
                        if (noOfLogs * NO_OF_LOG_CHAR + NO_OF_LOG_CHAR < message.length) {
                            Log.d(
                                TAG,
                                message.substring(
                                    noOfLogs * NO_OF_LOG_CHAR,
                                    noOfLogs * NO_OF_LOG_CHAR + NO_OF_LOG_CHAR
                                )
                            )
                        } else {
                            Log.d(
                                TAG,
                                message.substring(noOfLogs * NO_OF_LOG_CHAR, message.length)
                            )
                        }
                    }
                } else {
                    Log.d(TAG, message)
                }
            }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        //loggingInterceptor.redactHeader("Authorization")
        loggingInterceptor.redactHeader("Cookie")
        return loggingInterceptor
    }

    private fun getDispatcher(): Dispatcher {
        return sDispatcher ?: Dispatcher()
    }
}