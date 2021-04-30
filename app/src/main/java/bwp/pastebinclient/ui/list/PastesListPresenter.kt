package bwp.pastebinclient.ui.list

import bwp.pastebinclient.interactor.PastesInteractor
import bwp.pastebinclient.interactor.event.CreateUserKeyEvent
import bwp.pastebinclient.interactor.event.GetPastesEvent
import bwp.pastebinclient.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class PastesListPresenter @Inject constructor(
    private val executor: Executor,
    private val pastesInteractor: PastesInteractor) : Presenter<PastesListScreen>() {

    override fun attachScreen(screen: PastesListScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    fun showPastes(userKey: String?) {
        if (userKey != null) {
            executor.execute {
                pastesInteractor.getPastes(userKey)
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGetPastesEvent(event: GetPastesEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showPastesFailed(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.pastes != null) {
                    screen?.showPastes(event.pastes)
                }
            }
        }
    }

    fun login(username: String, password: String) {
        executor.execute {
            pastesInteractor.createUserKey(username, password)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCreateUserKeyEvent(event: CreateUserKeyEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.loginFailed(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.userKey != null) {
                    screen?.loginSuccess(event.userKey.orEmpty())
                }
            }
        }
    }
}