package bwp.pastebinclient

import bwp.pastebinclient.interactor.InteractorModule
import bwp.pastebinclient.network.NetworkModule
import bwp.pastebinclient.ui.list.PastesListActivity
import bwp.pastebinclient.ui.UIModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, NetworkModule::class, InteractorModule::class])
interface PastebinClientApplicationComponent {
    fun inject(pastesListActivity: PastesListActivity)
}