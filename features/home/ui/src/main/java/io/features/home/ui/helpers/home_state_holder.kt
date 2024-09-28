package io.features.home.ui.helpers

import io.features.home.domain.entities.UrlEntity

/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.features.home.ui.helpers
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

data class HomeStateHolder(
    val isLoading: Boolean = false,
    val success: List<UrlEntity> = emptyList(),
    val error: String = ""
)