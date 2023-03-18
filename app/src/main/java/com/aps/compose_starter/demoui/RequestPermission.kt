package com.aps.compose_starter.demoui

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@ExperimentalPermissionsApi
@Composable
fun RequestPermission(){
    val permissionState = rememberMultiplePermissionsState(permissions =
    listOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    )
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver{_,event ->
            if(event == Lifecycle.Event.ON_START){
                permissionState.launchMultiplePermissionRequest()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })

    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        permissionState.permissions.forEach { permission ->
            when(permission.permission){
                Manifest.permission.CAMERA->{
                    when{
                        permission.hasPermission ->{Text(text = "Camera permission Accepted")}
                        permission.shouldShowRationale ->{
                            Text(text = "Camera permission is needed to access the camera")
                            }
                        !permission.isPermanentlyDenied()->{
                            Text(text = "Camera permission was permanently" +
                                    "denied.You can enable it in the app" +
                                    "settings")
                        }
                    }
                }
                Manifest.permission.RECORD_AUDIO ->{
                    when{
                        permission.hasPermission ->{Text(text = "Record autdio permission Accepted")}
                        permission.shouldShowRationale ->{
                            Text(text = "Record autdio is needed to access the camera")
                            }
                        !permission.isPermanentlyDenied()->{
                            Text(text = "Record autdio permission was permanently" +
                                    "denied.You can enable it in the app" +
                                    "settings")
                        }
                    }
                }
            }
        }

    }
}