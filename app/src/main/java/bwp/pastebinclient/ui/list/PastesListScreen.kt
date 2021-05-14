package bwp.pastebinclient.ui.list

import bwp.pastebinclient.model.PasteInfo

interface PastesListScreen {
    fun showPastes(pastes: List<PasteInfo>?)
    fun showPastesFailed(error: Throwable?)

    fun loginSuccess(userKey: String)
    fun loginFailed(error: Throwable?)
}