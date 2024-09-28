package io.features.home.domain.entities

import java.util.UUID

/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.features.home.domain.entities
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

data class UrlEntity(
    var id: String,
    var url: String,
    var isHalt: Boolean,
    var severity: Int,
    var lastChecked: Long,
    var status: Int,
    var createdAt: Long,
    var updatedAt: Long,
    var totalFailures: Long,
    var totalHitsSince: Long,
    var lastNetworkUsed: String
)