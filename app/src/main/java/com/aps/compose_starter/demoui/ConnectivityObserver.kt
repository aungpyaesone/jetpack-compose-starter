package com.aps.compose_starter.demoui

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver{
    fun observe() : Flow<Status>

    enum class Status{
        Available,Unavailable,Losing,Lost
    }
}