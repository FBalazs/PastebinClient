package bwp.pastebinclient.ui.details

interface PasteDetailsScreen {
    fun showPaste(rawPaste: String)
    fun showNetworkError(error: Throwable?)
    fun deleteSuccess()
    fun deleteFailed(error: Throwable?)
}