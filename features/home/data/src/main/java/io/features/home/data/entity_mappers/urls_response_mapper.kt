package io.features.home.data.entity_mappers

import io.core.network.dto.UrlsDto
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

fun UrlsDto.toUrlEntity(): UrlEntity {
    return UrlEntity(
        id = this.id,
        url = this.url,
        isHalt = this.isHalt,
        severity = this.severity,
        lastChecked = this.lastChecked,
        status = this.status,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        totalFailures = this.totalFailures,
        totalHitsSince = this.totalHitsSince,
        lastNetworkUsed = this.lastNetworkUsed
    )
}