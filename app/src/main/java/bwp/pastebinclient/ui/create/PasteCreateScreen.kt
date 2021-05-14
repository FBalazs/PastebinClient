package bwp.pastebinclient.ui.create

interface PasteCreateScreen {
    fun createSuccessful(pasteKey: String)
    fun createFailed(error: Throwable?)
}