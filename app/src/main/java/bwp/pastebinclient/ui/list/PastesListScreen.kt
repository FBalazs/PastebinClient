package bwp.pastebinclient.ui.list

import bwp.pastebinclient.model.PasteInfo

interface PastesListScreen {
    fun showPastes(pastes: List<PasteInfo>?)
    fun showNetworkError(errorMsg: String)
}