package bwp.pastebinclient.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "user")
data class UserInfo(
    @field:Element(name = "user_name")
    var name: String = "",
    @field:Element(name = "user_format_short")
    var defaultFormatShort: String = "",
    @field:Element(name = "user_expiration")
    var defaultExpiration: String = "",
    @field:Element(name = "user_avatar_url")
    var avatarUrl: String = "",
    @field:Element(name = "user_private")
    var defaultPrivate: Int = 0,
    @field:Element(name = "user_website")
    var website: String = "",
    @field:Element(name = "user_email")
    var email: String = "",
    @field:Element(name = "user_location")
    var location: String = "",
    @field:Element(name = "user_account_type")
    var accountType: Int = 0
)