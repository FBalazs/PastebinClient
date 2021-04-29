package bwp.pastebinclient.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import bwp.pastebinclient.databinding.ActivityPasteListBinding
import bwp.pastebinclient.injector
import bwp.pastebinclient.model.PasteInfo
import javax.inject.Inject

class PastesListActivity : AppCompatActivity(), PastesListScreen {

    @Inject
    lateinit var pastesListPresenter: PastesListPresenter

    private lateinit var binding: ActivityPasteListBinding

    private val adapter: PastesListAdapter = PastesListAdapter { pasteOnClick(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPastesList.adapter = adapter
        binding.fabCreatePaste.setOnClickListener { fabOnClick() }
    }

    override fun onStart() {
        super.onStart()
        injector.inject(this)
        pastesListPresenter.attachScreen(this)

//        pastesListPresenter.showOnlyLocalPastes() // TODO remove temp call
        pastesListPresenter.showOnlyUserPastes("<insert your user key here>")
    }

    override fun onStop() {
        pastesListPresenter.detachScreen()
        super.onStop()
    }

    private fun pasteOnClick(pasteInfo: PasteInfo) {
        Toast.makeText(this, "pasteOnClick", Toast.LENGTH_LONG).show()
    }

    private fun fabOnClick() {
        Toast.makeText(this, "fabOnClick", Toast.LENGTH_LONG).show()
    }

    override fun showPastes(pastes: List<PasteInfo>?) {
        adapter.submitList(pastes)
    }

    override fun showNetworkError(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }
}