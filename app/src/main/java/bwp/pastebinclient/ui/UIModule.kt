package bwp.pastebinclient.ui

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context
}