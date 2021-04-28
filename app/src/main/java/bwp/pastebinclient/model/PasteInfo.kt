package bwp.pastebinclient.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "paste")
data class PasteInfo(
    @Element(name = "paste_key")
    val key: String,
    @Element(name = "paste_url")
    val url: String,
    @Element(name = "paste_title")
    val title: String,
    @Element(name = "paste_date")
    val date: Long,
    @Element(name = "paste_expire_date")
    val expireDate: Long,
    @Element(name = "paste_private")
    val private: Int,
    @Element(name = "paste_size")
    val size: Long,
    @Element(name = "paste_format_long")
    val formatLong: String,
    @Element(name = "paste_format_short")
    val formatShort: String,
    @Element(name = "paste_hits")
    val hits: Long
) {

    val isPrivate: Boolean = private == 2
}