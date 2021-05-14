package bwp.pastebinclient.interactor.event

data class CreatePasteEvent(
    var code: Int = 0,
    var pasteKey: String? = null,
    var throwable: Throwable? = null
)