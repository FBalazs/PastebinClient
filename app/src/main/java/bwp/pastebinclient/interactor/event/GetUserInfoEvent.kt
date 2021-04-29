package bwp.pastebinclient.interactor.event

import bwp.pastebinclient.model.UserInfo

data class GetUserInfoEvent(
    var code: Int = 0,
    var userInfo: UserInfo? = null,
    var throwable: Throwable? = null
)