package io.core.network

import io.core.network.NetworkConstants.API_AUTH_TYPE
import io.core.network.NetworkConstants.TAG
import okhttp3.Request
import okio.Buffer
import timber.log.Timber
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.nio.charset.Charset
import java.security.NoSuchAlgorithmException

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

internal fun getMd5String(stringToConvert: String): String {
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

internal fun printPostmanFormattedLog(request: Request) {
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
        Timber.d(TAG, paramsString.toString())
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