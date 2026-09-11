package com.axion.calc.core.role

import android.app.Activity
import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.os.Build

/**
 * Helper for requesting the system calculator role.
 *
 * ROLE_SYSTEM_CALCULATOR is not exposed as a public constant in the SDK
 * android.jar (even on API 35+). Use the official role name string instead.
 * Availability is still gated by isRoleAvailable() at runtime.
 */
object DefaultCalculatorHelper {

    /** Official role name used by the system for the default calculator app. */
    private const val ROLE_SYSTEM_CALCULATOR = "android.app.role.SYSTEM_CALCULATOR"

    /** Role request is only meaningful on API 29+ (RoleManager introduction). */
    fun isSupported(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    fun isDefault(context: Context): Boolean {
        if (!isSupported()) return false
        val roleManager = context.getSystemService(RoleManager::class.java) ?: return false
        return roleManager.isRoleAvailable(ROLE_SYSTEM_CALCULATOR) &&
            roleManager.isRoleHeld(ROLE_SYSTEM_CALCULATOR)
    }

    fun requestRoleIntent(context: Context): Intent? {
        if (!isSupported()) return null
        val roleManager = context.getSystemService(RoleManager::class.java) ?: return null
        if (!roleManager.isRoleAvailable(ROLE_SYSTEM_CALCULATOR)) return null
        return roleManager.createRequestRoleIntent(ROLE_SYSTEM_CALCULATOR)
    }

    fun requestRole(activity: Activity): Boolean {
        val intent = requestRoleIntent(activity) ?: return false
        @Suppress("DEPRECATION")
        activity.startActivityForResult(intent, REQUEST_CODE)
        return true
    }

    const val REQUEST_CODE = 7001
}
