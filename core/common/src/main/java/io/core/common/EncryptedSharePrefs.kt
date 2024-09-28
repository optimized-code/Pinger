package io.core.common

import android.content.Context
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

/**
 **************************************************************
 * www.optimizedcode.io
 * Kotlin
 *
 * @author Ehtisham Ahmad Qureshi
 * @package io.core.common
 * @date 28-Sep-2024
 * @copyright 2024 Optimized code (https://www.optimizedcode.io)
 * @license Open source
 ***************************************************************
 */

enum class PREFSTYPE {
    USER,
    CONFIG,
    DEFAULT
}

object PingoEncryptedSharePrefs {
    fun createEncryptedSharedPrefs(context: Context, emanelifyek: String) {
        MasterKeys.getOrCreate(
            KeyGenParameterSpec.Builder(
                "PingoSecureKeys",
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setKeySize(256).build()
        )

        val preferences = EncryptedSharedPreferences.create(
            emanelifyek,
            emanelifyek,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        preferences
            .edit()
            .putString(emanelifyek, "none")
            .apply()
    }

    private inline fun EncryptedSharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    operator fun EncryptedSharedPreferences.set(key: String, value: Any?) =
        when (value) {
            is String -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }

    inline operator fun <reified T : Any> EncryptedSharedPreferences.get(key: String): T =
        when (T::class) {
            String::class -> getString(key, "") as T
            Int::class -> getInt(key, -1) as T
            Float::class -> getFloat(key, -1f) as T
            Boolean::class -> getBoolean(key, false) as T
            Long::class -> getLong(key, -1L) as T
            else -> throw UnsupportedOperationException("Still implementing")
        }


    fun EncryptedSharedPreferences.clear() {
        edit { it.clear().apply() }
    }

    fun EncryptedSharedPreferences.remove(key: String) {
        edit { it.remove(key) }
    }
}

fun getEnvVariable(key: String): String{
    return System.getenv(key) ?: "none"
}

object PingoSharedPrefs {

    private const val USER_PREFS = "user_prefs"
    private const val CONFIG_PREFS = "config_prefs"
    private const val DEFAULT_PREFS = "default_prefs"

    private lateinit var userPrefs: SharedPreferences
    private lateinit var configPrefs: SharedPreferences
    private lateinit var defaultPrefs: SharedPreferences

    fun start(context: Context) {
        userPrefs = context.getSharedPreferences(USER_PREFS, MODE_PRIVATE)
        configPrefs = context.getSharedPreferences(CONFIG_PREFS, MODE_PRIVATE)
        defaultPrefs = context.getSharedPreferences(DEFAULT_PREFS, MODE_PRIVATE)
    }

    fun prefs(type: PREFSTYPE = PREFSTYPE.DEFAULT): SharedPreferences {
        return when (type) {
            PREFSTYPE.USER -> userPrefs
            PREFSTYPE.CONFIG -> configPrefs
            else -> defaultPrefs
        }
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    operator fun SharedPreferences.set(key: String, value: Any?) =
        when (value) {
            is String -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }

    inline operator fun <reified T : Any> SharedPreferences.get(key: String): T =
        when (T::class) {
            String::class -> getString(key, "") as T
            Int::class -> getInt(key, -1) as T
            Float::class -> getFloat(key, -1f) as T
            Boolean::class -> getBoolean(key, false) as T
            Long::class -> getLong(key, -1L) as T
            else -> throw UnsupportedOperationException("Still implementing")
        }


    fun SharedPreferences.clear(list: List<PREFSTYPE> = listOf(PREFSTYPE.DEFAULT)) {
        repeat(list.size) {
            edit { it.clear().apply() }
        }
    }

    fun SharedPreferences.remove(key: String) {
        edit { it.remove(key) }
    }
}