package bwp.pastebinclient.interactor.event

data class DeletePasteEvent(
    var code: Int = 0,
    var throwable: Throwable? = null
)