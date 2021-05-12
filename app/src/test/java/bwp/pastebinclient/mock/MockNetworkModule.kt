package bwp.pastebinclient.mock

import bwp.pastebinclient.network.PasteApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class MockNetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun providePasteApi(client: OkHttpClient): PasteApi = MockPasteApi()
}