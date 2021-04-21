package bwp.pastebinclient.ui

import bwp.pastebinclient.model.PasteInfo

class PastePresenter : Presenter<PasteScreen>() {

    fun showPastes(pasteInfo: PasteInfo?, rawPaste: String?) {
        screen?.showPaste(pasteInfo, rawPaste)
    }
}