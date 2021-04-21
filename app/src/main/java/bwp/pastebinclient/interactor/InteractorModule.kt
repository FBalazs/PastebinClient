package bwp.pastebinclient.interactor

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun providePastesInteractor() = PastesInteractor()
}