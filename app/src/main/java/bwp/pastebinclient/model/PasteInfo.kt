package bwp.pastebinclient.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

@Root(name = "paste")
data class PasteInfo(
    @field:Element(name = "paste_key")
    var key: String = "",
    @field:Element(name = "paste_url")
    var url: String = "",
    @field:Element(name = "paste_title")
    var title: String = "",
    @field:Element(name = "paste_date")
    var date: Long = 0,
    @field:Element(name = "paste_expire_date")
    var expireDate: Long = 0,
    @field:Element(name = "paste_private")
    var private: Int = 0,
    @field:Element(name = "paste_size")
    var size: Long = 0,
    @field:Element(name = "paste_format_long")
    var formatLong: String = "",
    @field:Element(name = "paste_format_short")
    var formatShort: String = "",
    @field:Element(name = "paste_hits")
    var hits: Long = 0
) : Serializable {

    val isPrivate: Boolean = private == 2
}