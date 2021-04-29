package bwp.pastebinclient.interactor.event

data class GetRawPasteEvent(
    var code: Int = 0,
    var rawPasteCode: String? = null,
    var throwable: Throwable? = null
)