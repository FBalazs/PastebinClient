package bwp.pastebinclient.interactor.event

import bwp.pastebinclient.model.UserInfo

data class GetRawPasteEvent(
    var code: Int = 0,
    var rawPasteCode: String? = null,
    var throwable: Throwable? = null
)