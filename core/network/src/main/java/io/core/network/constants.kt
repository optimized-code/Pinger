package io.core.network

import okhttp3.Dispatcher

/*
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

internal object NetworkConstants {
    const val CONNECT_TIMEOUT_MULTIPLIER = 1
    const val DEFAULT_CONNECT_TIMEOUT_IN_SEC = 60 * CONNECT_TIMEOUT_MULTIPLIER
    const val DEFAULT_WRITE_TIMEOUT_IN_SEC = 60 * CONNECT_TIMEOUT_MULTIPLIER
    const val DEFAULT_READ_TIMEOUT_IN_SEC = 60 * CONNECT_TIMEOUT_MULTIPLIER
    const val NO_OF_LOG_CHAR = 2000
    const val API_USER_NAME = "optimized_code"
    const val API_PASSWORD = "WwF4HJWh9pr0ry0v"
    const val API_AUTH_TYPE = "sha256"
    var authKey: String = ""
    const val TAG = "Pingo_network"

    // FIREBASE DATABASE OBJECTS ///////////////////////////////////////////////////////////////////
    const val URLS = "urls"
    const val LOGS = "logs"
}
