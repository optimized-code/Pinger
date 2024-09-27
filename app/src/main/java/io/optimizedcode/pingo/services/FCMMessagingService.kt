package io.optimizedcode.pingo.services

import com.google.firebase.messaging.FirebaseMessagingService
import timber.log.Timber

/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.optimizedcode.pingo.services
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

class FCMMessagingService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.d("New token: $token")
        //sendRegistrationToServer(token)
    }
}