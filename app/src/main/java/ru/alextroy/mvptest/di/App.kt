package ru.alextroy.mvptest.di

import android.app.Application

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    private lateinit var netComponent: NetComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        netComponent = DaggerNetComponent.factory().create(this)
    }

    fun getComponent(): NetComponent = netComponent
}