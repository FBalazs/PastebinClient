package bwp.pastebinclient.model

data class PasteInfo(
    var key: String?,
    var url: String?,
    var title: String?,
    var date: Long?,
    var expireDate: Long?,
    var isPrivate: Boolean?,
    var size: Long?,
    var formatLong: String?,
    var formatShort: String?,
    var hits: Long?
)