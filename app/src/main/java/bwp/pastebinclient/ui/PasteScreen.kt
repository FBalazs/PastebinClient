package bwp.pastebinclient.ui

import bwp.pastebinclient.model.PasteInfo

interface PasteScreen {
    fun showPaste(paste: PasteInfo?, rawPaste: String?)
}