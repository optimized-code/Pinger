package io.core.common

/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.core.common
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

sealed class UiEvents<T>(val message: String? = null, val data: T? = null) {
    class Loading<T>(): UiEvents<T>()
    class Success<T>(data: T): UiEvents<T>(data = data)
    class Error<T>(message: String): UiEvents<T>(message = message)
}