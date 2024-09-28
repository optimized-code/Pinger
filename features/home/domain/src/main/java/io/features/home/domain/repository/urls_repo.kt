package io.features.home.domain.repository

import io.features.home.domain.entities.UrlEntity

/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.features.home.domain.repository
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

interface UrlsRepo {
    suspend fun getAllUrls(): List<UrlEntity>
}