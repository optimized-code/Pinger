package io.features.home.data.repository

import com.fasterxml.jackson.core.type.TypeReference
import io.core.network.data_providers.UrlsDataProvider
import io.features.home.data.entity_mappers.toUrlEntityList
import io.features.home.domain.entities.UrlEntity
import io.features.home.domain.repository.UrlsRepo
import javax.inject.Inject
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.core.network.dto.UrlsDto
import io.features.home.data.entity_mappers.toUrlEntity
import kotlinx.serialization.json.Json
import timber.log.Timber


/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.features.home.data.repository
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

class UrlsRepoImpl @Inject constructor(private val urlsDataProvider: UrlsDataProvider) : UrlsRepo {
    override suspend fun getAllUrls(): List<UrlEntity> {
        val result = urlsDataProvider.getAllUrls()
        val resultDictionary = Json.decodeFromString<Map<String, UrlsDto>>(result.toString())
        val urlEntityList = resultDictionary.map {
            it.value.toUrlEntity()
        }
        return urlEntityList
    }
}