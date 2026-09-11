package com.axion.calc.core.role

import android.app.Activity
import android.app.role.RoleManager
import android.content.Context
import android.content.Intent
import android.os.Build

object DefaultCalculatorHelper {
    fun isSupported(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

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
        activity.startActivityForResult(intent, REQUEST_CODE)
        return true
    }

    const val REQUEST_CODE = 7001
}
