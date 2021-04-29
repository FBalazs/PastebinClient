package bwp.pastebinclient.ui

import android.content.Context
import bwp.pastebinclient.interactor.PastesInteractor
import bwp.pastebinclient.ui.details.PasteDetailsPresenter
import bwp.pastebinclient.ui.list.PastesListPresenter
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
    fun pastesListPresenter(executor: Executor, pastesInteractor: PastesInteractor) = PastesListPresenter(executor, pastesInteractor)

    @Provides
    @Singleton
    fun pasteDetailsPresenter(executor: Executor, pastesInteractor: PastesInteractor) = PasteDetailsPresenter(executor, pastesInteractor)

    @Provides
    @Singleton
    fun networkExecutor(): Executor = Executors.newFixedThreadPool(1)
}