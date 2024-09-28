package io.features.home.data.repository

import io.core.network.data_providers.UrlsDataProvider
import io.features.home.data.entity_mappers.toUrlEntityList
import io.features.home.domain.entities.UrlEntity
import io.features.home.domain.repository.UrlsRepo
import kotlinx.serialization.json.Json
import javax.inject.Inject

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
        return urlsDataProvider.getAllUrls().toUrlEntityList()
    }
}