package bwp.pastebinclient.interactor.event

import bwp.pastebinclient.model.PasteInfo

data class GetPastesEvent(
    var code: Int = 0,
    var pastes: List<PasteInfo>? = null,
    var throwable: Throwable? = null
)