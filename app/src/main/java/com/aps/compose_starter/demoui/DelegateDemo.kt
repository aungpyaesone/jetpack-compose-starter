package com.aps.compose_starter.demoui

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

interface DeepLinkHandler{
    fun handleDeepLink(activity: ComponentActivity,intent: Intent?)
}

class DeepLinkHandlerImpl : DeepLinkHandler{
    override fun handleDeepLink(activity: ComponentActivity, intent: Intent?) {

    }

}

interface AnalyticLogger{
    fun registerLifecycleOwner(owner: LifecycleOwner)
}

class AnalyticsLoggerImpl: AnalyticLogger,LifecycleEventObserver{
    override fun registerLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_RESUME -> println("user open the screen")
            Lifecycle.Event.ON_PAUSE -> println("User leaves the screen")
            else -> Unit
        }
    }

}