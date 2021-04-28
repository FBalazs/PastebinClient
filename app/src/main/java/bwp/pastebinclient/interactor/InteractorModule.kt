package bwp.pastebinclient.interactor

import bwp.pastebinclient.network.PasteApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun providePastesInteractor(pasteApi: PasteApi) = PastesInteractor(pasteApi)
}