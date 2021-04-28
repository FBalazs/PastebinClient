package bwp.pastebinclient.ui

interface PasteScreen {
    fun showPaste(rawPaste: String)
    fun showNetworkError(errorMsg: String)
}