package io.optimizedcode.pingo.application

import android.app.Application
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp
import io.core.common.PingoEncryptedSharePrefs
import io.core.common.getEnvVariable
import io.optimizedcode.pingo.BuildConfig
import timber.log.Timber
import timber.log.Timber.*
import timber.log.Timber.Forest.plant


/*
**************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.optimizedcode.pingo.application
 * @date 27-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

@HiltAndroidApp
class PingoApp : Application() {

    val TAG = PingoApp::class.java.name

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = "New token: $token"
            Timber.d(TAG, msg)
        })

        PingoEncryptedSharePrefs.createEncryptedSharedPrefs(
            this,
            getEnvVariable(BuildConfig.ecp)
        )
    }
}