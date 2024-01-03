package com.example.mylocations.common

import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class PermissionManager(private val fragment: Fragment) {

    private var onPermissionsGranted: (() -> Unit)? = null

    fun setOnPermissionsGrantedListener(listener: () -> Unit) {
        onPermissionsGranted = listener
    }

    private var onPermissionsDene: (() -> Unit)? = null

    fun setOnPermissionsDeneListener(listener: () -> Unit) {
        onPermissionsDene = listener
    }

    fun checkAndRequestPermissions(permissions: List<String>) {
        val permissionsToRequest = permissions.filter {
            ContextCompat.checkSelfPermission(
                fragment.requireContext(),
                it
            ) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (permissionsToRequest.isNotEmpty()) {
            requestPermissions(permissionsToRequest.toList())
        } else {
            onPermissionsGranted?.invoke()
        }
    }

    private val requestPermissionLauncher =
        fragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            handlePermissionsResult(permissions)
        }

    private fun requestPermissions(permissions: List<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissionLauncher.launch(permissions.toTypedArray())
        }
    }

    private fun handlePermissionsResult(permissions: Map<String, Boolean>) {
        val allPermissionsGranted = permissions.all { it.value }
        if (allPermissionsGranted) {
            onPermissionsGranted?.invoke()
        }else{
            onPermissionsDene?.invoke()
        }
    }
}