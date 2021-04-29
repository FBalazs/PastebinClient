package bwp.pastebinclient.ui.details

interface PasteDetailsScreen {
    fun showPaste(rawPaste: String)
    fun showNetworkError(errorMsg: String)
}