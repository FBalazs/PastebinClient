package bwp.pastebinclient.ui.details

import bwp.pastebinclient.interactor.PastesInteractor
import bwp.pastebinclient.interactor.event.GetRawPasteEvent
import bwp.pastebinclient.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class PasteDetailsPresenter @Inject constructor(
        private val executor: Executor,
        private val pastesInteractor: PastesInteractor) : Presenter<PasteDetailsScreen>() {

    override fun attachScreen(screen: PasteDetailsScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun showPaste(pasteKey: String) {
        executor.execute {
            pastesInteractor.getPublicRawPasteCode(pasteKey)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetRawPasteEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showNetworkError(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.rawPasteCode != null) {
                    screen?.showPaste(event.rawPasteCode.orEmpty())
                }
            }
        }
    }
}