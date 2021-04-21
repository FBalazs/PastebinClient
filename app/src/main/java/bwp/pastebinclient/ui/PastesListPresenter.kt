package bwp.pastebinclient.ui

import bwp.pastebinclient.model.PasteInfo

class PastesListPresenter : Presenter<PastesListScreen>() {

    fun showPastes(pastes: List<PasteInfo>?) {
        screen?.showPastes(pastes)
    }
}