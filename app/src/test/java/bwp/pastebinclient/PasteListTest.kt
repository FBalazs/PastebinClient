package bwp.pastebinclient

import bwp.pastebinclient.interactor.PastesInteractor
import bwp.pastebinclient.mock.MockPasteApi
import bwp.pastebinclient.ui.list.PastesListPresenter
import bwp.pastebinclient.ui.list.PastesListScreen
import bwp.pastebinclient.utils.UiExecutor
import bwp.pastebinclient.utils.mock
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class PasteListTest {
    private val listPresenter = PastesListPresenter(UiExecutor(), PastesInteractor(MockPasteApi()))
    private lateinit var listScreen: PastesListScreen

    @Before
    fun setUp() {
        listScreen = mock()
        listPresenter.attachScreen(listScreen)
    }

    @After
    fun tearDown() {
        listPresenter.detachScreen()
    }

    @Test
    fun testPastes() {
        listPresenter.showPastes("key")
        verify(listScreen).showPastes(any())
    }
}