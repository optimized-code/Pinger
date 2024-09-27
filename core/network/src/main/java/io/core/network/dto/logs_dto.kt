package io.core.network.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class LogsDto(
    @JsonProperty("id")
    var id: UUID,

    @JsonProperty("urlId")
    var urlId: UUID,

    @JsonProperty("tookTime")
    var tookTime: Long,

    @JsonProperty("numberOfPacketsSent")
    var numberOfPacketsSent: Int,

    @JsonProperty("numberOfPacketsReceived")
    var numberOfPacketsReceived: Int,

    @JsonProperty("networkUsed")
    var networkUsed: Int,

    @JsonProperty("createdAt")
    var createdAt: Long
)
