package com.axion.calc.core.role

import android.app.Activity
import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.os.Build

/**
 * Helper for requesting the system calculator role (API 35+).
 * ROLE_SYSTEM_CALCULATOR was introduced in Android 15 (API 35).
 */
object DefaultCalculatorHelper {

    /** Role is only available starting from Android 15 (API 35). */
    fun isSupported(): Boolean = Build.VERSION.SDK_INT >= 35 // Build.VERSION_CODES.VANILLA_ICE_CREAM

    fun isDefault(context: Context): Boolean {
        if (!isSupported()) return false
        val roleManager = context.getSystemService(RoleManager::class.java) ?: return false
        return roleManager.isRoleAvailable(RoleManager.ROLE_SYSTEM_CALCULATOR) &&
            roleManager.isRoleHeld(RoleManager.ROLE_SYSTEM_CALCULATOR)
    }

    fun requestRoleIntent(context: Context): Intent? {
        if (!isSupported()) return null
        val roleManager = context.getSystemService(RoleManager::class.java) ?: return null
        if (!roleManager.isRoleAvailable(RoleManager.ROLE_SYSTEM_CALCULATOR)) return null
        return roleManager.createRequestRoleIntent(RoleManager.ROLE_SYSTEM_CALCULATOR)
    }

    fun requestRole(activity: Activity): Boolean {
        val intent = requestRoleIntent(activity) ?: return false
        @Suppress("DEPRECATION")
        activity.startActivityForResult(intent, REQUEST_CODE)
        return true
    }

    const val REQUEST_CODE = 7001
}
