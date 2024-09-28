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

package io.core.network

import com.fasterxml.jackson.databind.node.ObjectNode
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET(Endpoints.URLS)
    suspend fun getAllUrls(): ObjectNode

    @POST(Endpoints.URLS)
    suspend fun createUrl(): String

    @DELETE(Endpoints.URLS)
    suspend fun deleteUrl(): String
}