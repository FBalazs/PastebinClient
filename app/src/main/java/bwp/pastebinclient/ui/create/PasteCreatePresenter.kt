package bwp.pastebinclient.ui.create

import bwp.pastebinclient.interactor.PastesInteractor
import bwp.pastebinclient.interactor.event.CreatePasteEvent
import bwp.pastebinclient.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class PasteCreatePresenter @Inject constructor(
    private val executor: Executor,
    private val pastesInteractor: PastesInteractor
) : Presenter<PasteCreateScreen>() {

    override fun attachScreen(screen: PasteCreateScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun createPaste(pasteCode: String,
                    pasteTitle: String? = null,
                    pasteFormat: String? = null,
                    apiPastePrivate: Int? = null,
                    expireTime: String? = null,
                    userKey: String? = null) {
        executor.execute {
            pastesInteractor.createPaste(pasteCode, pasteTitle, pasteFormat, apiPastePrivate, expireTime, userKey)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: CreatePasteEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.createFailed(event.throwable)
            }
        } else {
            if (screen != null) {
                if (event.pasteKey != null) {
                    screen?.createSuccessful(event.pasteKey.orEmpty())
                } else {
                    screen?.createFailed(null)
                }
            }
        }
    }
}