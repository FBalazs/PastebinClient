package bwp.pastebinclient.ui

import bwp.pastebinclient.model.PasteInfo

interface PastesListScreen {
    fun showPastes(pastes: List<PasteInfo>?)
}