package io.features.home.domain.usecase

import io.core.common.UiEvents
import io.features.home.domain.repository.UrlsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.features.home.domain.usecase
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

class UrlsUseCase @Inject constructor(private val urlsRepo: UrlsRepo) {
    operator fun invoke() = flow {
        emit(UiEvents.Loading())
        val result = urlsRepo.getAllUrls()
        emit(UiEvents.Success(result))
    }.catch {
        emit(UiEvents.Error(it.message ?: "Something went wrong!"))
    }.flowOn(Dispatchers.IO)
}