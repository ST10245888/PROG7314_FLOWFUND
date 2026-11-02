
package com.example.flowfund_part2

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.flowfund_part2.data.preferences.SettingsRepository
import com.example.flowfund_part2.data.preferences.UserSettings
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

/**
 * Tests saving, retrieving, and validating FlowFund user preferences.
 *
 * These are Android instrumentation tests because they use an Android
 * application context and Jetpack DataStore.
 */
class SettingsRepositoryTest {

    private val context: Context
        get() = ApplicationProvider.getApplicationContext()

    private val repository: SettingsRepository
        get() = SettingsRepository(context)

    /**
     * Checks that saved settings can be read back with the same values.
     */
    @Test
    fun saveSettingsAndReadThemBack() = runBlocking {
        val expected = UserSettings(
            notificationsEnabled = false,
            biometricEnabled = true,
            preferredLanguage = "zu",
            preferredCurrency = "ZAR",
            reminderHoursBefore = 12
        )

        repository.saveSettings(expected)

        val actual = repository.settings.first()

        assertEquals(expected.notificationsEnabled, actual.notificationsEnabled)
        assertEquals(expected.biometricEnabled, actual.biometricEnabled)
        assertEquals(expected.preferredLanguage, actual.preferredLanguage)
        assertEquals(expected.preferredCurrency, actual.preferredCurrency)
        assertEquals(expected.reminderHoursBefore, actual.reminderHoursBefore)
    }

    /**
     * Checks that an unsupported language is rejected.
     */
    @Test
    fun rejectsUnsupportedLanguage() = runBlocking {
        val settings = UserSettings(
            notificationsEnabled = true,
            biometricEnabled = false,
            preferredLanguage = "xx",
            preferredCurrency = "ZAR",
            reminderHoursBefore = 24
        )

        val exception = assertThrows(IllegalArgumentException::class.java) {
            runBlocking {
                repository.saveSettings(settings)
            }
        }

        assertEquals(
            "Please select a supported language.",
            exception.message
        )
    }

    /**
     * Checks that an unsupported currency is rejected.
     */
    @Test
    fun rejectsUnsupportedCurrency() {
        val settings = UserSettings(
            notificationsEnabled = true,
            biometricEnabled = false,
            preferredLanguage = "en",
            preferredCurrency = "GBP",
            reminderHoursBefore = 24
        )

        val exception = assertThrows(IllegalArgumentException::class.java) {
            runBlocking {
                repository.saveSettings(settings)
            }
        }

        assertEquals(
            "Please select a supported currency.",
            exception.message
        )
    }

    /**
     * Checks that an unsupported reminder interval is rejected.
     */
    @Test
    fun rejectsUnsupportedReminderTime() {
        val settings = UserSettings(
            notificationsEnabled = true,
            biometricEnabled = false,
            preferredLanguage = "en",
            preferredCurrency = "ZAR",
            reminderHoursBefore = 7
        )

        val exception = assertThrows(IllegalArgumentException::class.java) {
            runBlocking {
                repository.saveSettings(settings)
            }
        }

        assertEquals(
            "Please select a supported reminder time.",
            exception.message
        )
    }
}
