package bwp.pastebinclient.ui

import android.content.Context
import bwp.pastebinclient.interactor.PastesInteractor
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun pastesListPresenter() = PastesListPresenter()

    @Provides
    @Singleton
    fun pastePresenter(executor: Executor, pastesInteractor: PastesInteractor) = PastePresenter(executor, pastesInteractor)

    @Provides
    @Singleton
    fun networkExecutor(): Executor = Executors.newFixedThreadPool(1)
}