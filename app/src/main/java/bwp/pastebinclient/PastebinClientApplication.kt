package bwp.pastebinclient

import android.app.Application
import bwp.pastebinclient.ui.UIModule

class PastebinClientApplication : Application() {
    lateinit var injector: PastebinClientApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerPastebinClientApplicationComponent.builder().uIModule(UIModule(this)).build()
    }
}