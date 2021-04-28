package bwp.pastebinclient.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import bwp.pastebinclient.R
import bwp.pastebinclient.injector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), PasteScreen {

    @Inject
    lateinit var pastePresenter: PastePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        injector.inject(this)
        pastePresenter.attachScreen(this)
        pastePresenter.showPaste("w0Hv8bSp")
    }

    override fun onStop() {
        pastePresenter.detachScreen()
        super.onStop()
    }

    override fun showPaste(rawPaste: String) {
        tvHelloWorld.text = rawPaste
    }

    override fun showNetworkError(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }
}