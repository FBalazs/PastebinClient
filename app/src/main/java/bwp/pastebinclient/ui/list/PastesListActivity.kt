package bwp.pastebinclient.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import bwp.pastebinclient.R
import bwp.pastebinclient.databinding.ActivityPasteListBinding
import bwp.pastebinclient.databinding.LoginDialogBinding
import bwp.pastebinclient.injector
import bwp.pastebinclient.model.PasteInfo
import javax.inject.Inject

class PastesListActivity : AppCompatActivity(), PastesListScreen {

    @Inject
    lateinit var pastesListPresenter: PastesListPresenter

    private lateinit var binding: ActivityPasteListBinding

    private val adapter: PastesListAdapter = PastesListAdapter { pasteOnClick(it) }

    private var userKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPastesList.adapter = adapter
        binding.fabCreatePaste.setOnClickListener { fabOnClick() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        return true
    }

    override fun onStart() {
        super.onStart()
        injector.inject(this)
        pastesListPresenter.attachScreen(this)

        pastesListPresenter.showOnlyLocalPastes() // TODO remove temp call
    }

    override fun onStop() {
        pastesListPresenter.detachScreen()
        super.onStop()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_login -> {
                clickLogin()
                true
            }
            R.id.menu_logout -> {
                clickLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    override fun showPastesFailed(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }

    private fun clickLogin() {
        val dialogBinding = LoginDialogBinding.inflate(layoutInflater)
        AlertDialog.Builder(this)
                .setView(dialogBinding.root)
                .setPositiveButton(R.string.login) { dialog, which ->
                    pastesListPresenter.login(
                            dialogBinding.etUsername.text.toString(),
                            dialogBinding.etPassword.text.toString())
                }
                .show()
    }

    private fun clickLogout() {
        userKey = null
    }

    override fun loginSuccess(userKey: String) {
        this.userKey = userKey
        pastesListPresenter.showOnlyUserPastes(userKey)
    }

    override fun loginFailed(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }
}