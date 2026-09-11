package com.axion.calc.data.preferences

enum class ThemePreference { FOLLOW_SYSTEM, LIGHT, DARK }
enum class LanguagePreference(val tag: String?) { FOLLOW_PHONE(null), ENGLISH("en"), INDONESIAN("id") }

data class UserPrefs(
    val theme: ThemePreference = ThemePreference.FOLLOW_SYSTEM,
    val language: LanguagePreference = LanguagePreference.FOLLOW_PHONE,
)
