package bwp.pastebinclient.interactor.event

import bwp.pastebinclient.model.UserInfo

data class GetUserPastesEvent(
    var code: Int = 0,
    var userInfo: UserInfo? = null,
    var throwable: Throwable? = null
)