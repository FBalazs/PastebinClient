package bwp.pastebinclient.ui.list

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import bwp.pastebinclient.R
import bwp.pastebinclient.databinding.ActivityPasteListBinding
import bwp.pastebinclient.databinding.LoginDialogBinding
import bwp.pastebinclient.injector
import bwp.pastebinclient.Constants
import bwp.pastebinclient.model.PasteInfo
import bwp.pastebinclient.ui.create.PasteCreateActivity
import bwp.pastebinclient.ui.details.PasteDetailsActivity
import javax.inject.Inject

class PastesListActivity : AppCompatActivity(), PastesListScreen {

    companion object {
        const val TAG = "PastesListActivity"
    }

    @Inject
    lateinit var pastesListPresenter: PastesListPresenter

    private lateinit var binding: ActivityPasteListBinding

    private val adapter: PastesListAdapter = PastesListAdapter { pasteOnClick(it) }

    private var userKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityPasteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPastesList.adapter = adapter
        binding.fabCreatePaste.setOnClickListener { fabOnClick() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG, "onCreateOptionsMenu")
        menuInflater.inflate(R.menu.list_menu, menu)
        return true
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
        injector.inject(this)

        userKey = getPreferences(Context.MODE_PRIVATE).getString(Constants.PREF_USER_KEY, null)

        pastesListPresenter.attachScreen(this)
        pastesListPresenter.showPastes(userKey)
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        pastesListPresenter.detachScreen()
        super.onStop()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onOptionsItemSelected")
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
        Log.d(TAG, "pasteOnClick")
        val intent = Intent(this, PasteDetailsActivity::class.java).apply {
            putExtra(Constants.EXTRA_USER_KEY, userKey)
            putExtra(Constants.EXTRA_PASTE_INFO, pasteInfo)
        }
        startActivity(intent)
    }

    private fun fabOnClick() {
        Log.d(TAG, "fabOnClick")
        val intent = Intent(this, PasteCreateActivity::class.java).apply {
            putExtra(Constants.EXTRA_USER_KEY, userKey)
        }
        startActivity(intent)
    }

    override fun showPastes(pastes: List<PasteInfo>?) {
        Log.d(TAG, "showPastes")
        adapter.submitList(pastes)
    }

    override fun showPastesFailed(errorMsg: String) {
        Log.d(TAG, "showPastesFailed")
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }

    private fun clickLogin() {
        Log.d(TAG, "clickLogin")
        val dialogBinding = LoginDialogBinding.inflate(layoutInflater)
        val loginActionListener = {
            pastesListPresenter.login(
                    dialogBinding.etUsername.text.toString(),
                    dialogBinding.etPassword.text.toString())
        }
        dialogBinding.etUsername.requestFocus()
        val alertDialog = AlertDialog.Builder(this)
                .setView(dialogBinding.root)
                .setPositiveButton(R.string.login) { dialog, which -> loginActionListener() }
                .create()
        dialogBinding.etPassword.setImeActionLabel("Login", KeyEvent.KEYCODE_ENTER)
        dialogBinding.etPassword.setOnEditorActionListener { v, actionId, event ->
            loginActionListener()
            alertDialog.dismiss()
            true
        }
        alertDialog.show()
    }

    private fun clickLogout() {
        Log.d(TAG, "clickLogout")
        userKey = null
        pastesListPresenter.showPastes(userKey)
        getPreferences(Context.MODE_PRIVATE).edit().remove(Constants.PREF_USER_KEY).apply()
    }

    override fun loginSuccess(userKey: String) {
        Log.d(TAG, "loginSuccess")
        this.userKey = userKey
        pastesListPresenter.showPastes(userKey)
        getPreferences(Context.MODE_PRIVATE).edit().putString(Constants.PREF_USER_KEY, userKey).apply()
    }

    override fun loginFailed(errorMsg: String) {
        Log.d(TAG, "loginFailed")
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }
}