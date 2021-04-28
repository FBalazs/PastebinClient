package bwp.pastebinclient.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "user")
data class UserInfo(
    @Element(name = "user_name")
    val name: String,
    @Element(name = "user_format_short")
    val defaultFormatShort: String,
    @Element(name = "user_expiration")
    val defaultExpiration: String,
    @Element(name = "user_avatar_url")
    val avatarUrl: String,
    @Element(name = "user_private")
    val defaultPrivate: Int,
    @Element(name = "user_website")
    val website: String,
    @Element(name = "user_email")
    val email: String,
    @Element(name = "user_location")
    val location: String,
    @Element(name = "user_account_type")
    val accountType: Int
)