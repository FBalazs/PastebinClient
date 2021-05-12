package bwp.pastebinclient

import bwp.pastebinclient.interactor.PastesInteractor
import bwp.pastebinclient.mock.MockPasteApi
import bwp.pastebinclient.ui.details.PasteDetailsPresenter
import bwp.pastebinclient.ui.details.PasteDetailsScreen
import bwp.pastebinclient.utils.UiExecutor
import bwp.pastebinclient.utils.mock
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class PasteDetailsTest {
    private val detailsPresenter = PasteDetailsPresenter(UiExecutor(), PastesInteractor(MockPasteApi()))
    private lateinit var detailsScreen: PasteDetailsScreen

    @Before
    fun setUp() {
        detailsScreen = mock()
        detailsPresenter.attachScreen(detailsScreen)
    }

    @After
    fun tearDown() {
        detailsPresenter.detachScreen()
    }

    @Test
    fun testDetails() {

    }
}