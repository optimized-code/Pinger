package io.features.home.data.entity_mappers

import io.features.home.domain.entities.UrlEntity
import kotlinx.serialization.json.Json

/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.features.home.data.entity_mappers
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

fun String.toUrlEntityList(): List<UrlEntity> {
    val resultDictionary = Json.decodeFromString<Map<String, UrlEntity>>(this)
    return resultDictionary.values.toList()
}