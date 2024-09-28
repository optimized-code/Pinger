package io.core.network.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.core.network.dto
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

// String  uniqueID = UUID.randomUUID().toString();
@Serializable
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class UrlsDto(
    @JsonProperty("id")
    var id: String,

    @JsonProperty("url")
    var url: String,

    @JsonProperty("isHalt")
    var isHalt: Boolean,

    @JsonProperty("severity")
    var severity: Int,

    @JsonProperty("lastChecked")
    var lastChecked: Long,

    @JsonProperty("status")
    var status: Int,

    @JsonProperty("createdAt")
    var createdAt: Long,

    @JsonProperty("updatedAt")
    var updatedAt: Long,

    @JsonProperty("totalFailures")
    var totalFailures: Long,

    @JsonProperty("totalHitsSince")
    var totalHitsSince: Long,

    @JsonProperty("lastNetworkUsed")
    var lastNetworkUsed: String
)
