package bwp.pastebinclient.interactor.event

data class CreateUserKeyEvent(
    var code: Int = 0,
    var userKey: String? = null,
    var throwable: Throwable? = null
)