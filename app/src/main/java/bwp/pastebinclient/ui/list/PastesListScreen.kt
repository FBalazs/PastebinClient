package bwp.pastebinclient.ui.list

import bwp.pastebinclient.model.PasteInfo

interface PastesListScreen {
    fun showPastes(pastes: List<PasteInfo>?)
    fun showPastesFailed(errorMsg: String)

    fun loginSuccess(userKey: String)
    fun loginFailed(errorMsg: String)
}