package bwp.pastebinclient.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "pastes")
data class PasteList(
    @field:ElementList(entry = "paste", inline = true)
    var pastes: List<PasteInfo> = ArrayList()
)