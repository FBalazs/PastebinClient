package bwp.pastebinclient.ui.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import bwp.pastebinclient.Constants
import bwp.pastebinclient.R
import bwp.pastebinclient.databinding.ActivityPasteListBinding
import bwp.pastebinclient.databinding.LoginDialogBinding
import bwp.pastebinclient.injector
import bwp.pastebinclient.model.PasteInfo
import bwp.pastebinclient.ui.create.PasteCreateActivity
import bwp.pastebinclient.ui.details.PasteDetailsActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject


class PastesListActivity : AppCompatActivity(), PastesListScreen {

    companion object {
        const val TAG = "PastesListActivity"
    }

    @Inject
    lateinit var pastesListPresenter: PastesListPresenter

    private lateinit var binding: ActivityPasteListBinding

    private val adapter: PastesListAdapter = PastesListAdapter { onClickPaste(it) }

    private var userKey: String? = null
        set(value) {
            field = value
            invalidateOptionsMenu()
        }

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        binding = ActivityPasteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPastesList.adapter = adapter
        binding.fabCreatePaste.setOnClickListener { onClickCreate() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG, "onCreateOptionsMenu")
        menuInflater.inflate(R.menu.list_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG, "onPrepareOptionsMenu")
        menu?.forEach { item ->
            when (item.itemId) {
                R.id.menu_login -> {
                    item.isVisible = userKey == null
                }
                R.id.menu_logout -> {
                    item.isVisible = userKey != null
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onOptionsItemSelected")
        return when (item.itemId) {
            R.id.menu_login -> {
                onClickLogin()
                true
            }
            R.id.menu_logout -> {
                onClickLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    private fun onClickCreate() {
        Log.d(TAG, "onClickCreate")
        val intent = Intent(this, PasteCreateActivity::class.java).apply {
            putExtra(Constants.EXTRA_USER_KEY, userKey)
        }
        startActivity(intent)
    }

    private fun onClickLogin() {
        Log.d(TAG, "onClickLogin")
        val dialogBinding = LoginDialogBinding.inflate(layoutInflater)
        val loginActionListener = {
            pastesListPresenter.login(
                dialogBinding.etUsername.text.toString(),
                dialogBinding.etPassword.text.toString()
            )
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

    private fun onClickLogout() {
        Log.d(TAG, "onClickLogout")
        userKey = null
        pastesListPresenter.showPastes(userKey)
        getPreferences(Context.MODE_PRIVATE).edit().remove(Constants.PREF_USER_KEY).apply()
    }

    private fun onClickPaste(pasteInfo: PasteInfo) {
        Log.d(TAG, "onClickPaste")

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, pasteInfo.key)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "paste")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

        val intent = Intent(this, PasteDetailsActivity::class.java).apply {
            putExtra(Constants.EXTRA_USER_KEY, userKey)
            putExtra(Constants.EXTRA_PASTE_INFO, pasteInfo)
        }
        startActivity(intent)
    }

    override fun showPastes(pastes: List<PasteInfo>?) {
        Log.d(TAG, "showPastes")
        adapter.submitList(pastes)
    }

    override fun showPastesFailed(error: Throwable?) {
        Log.d(TAG, "showPastesFailed")
        if (error != null) FirebaseCrashlytics.getInstance().recordException(error)
        Toast.makeText(this, "Could not load pastes: ${error?.message}", Toast.LENGTH_LONG).show()
    }

    override fun loginSuccess(userKey: String) {
        Log.d(TAG, "loginSuccess")

        val bundle = Bundle()
        bundle.putLong(FirebaseAnalytics.Param.SUCCESS, 1)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)

        this.userKey = userKey
        pastesListPresenter.showPastes(userKey)
        getPreferences(Context.MODE_PRIVATE).edit().putString(Constants.PREF_USER_KEY, userKey).apply()
    }

    override fun loginFailed(error: Throwable?) {
        Log.d(TAG, "loginFailed")

        val bundle = Bundle()
        bundle.putLong(FirebaseAnalytics.Param.SUCCESS, 0)
        bundle.putString("error", error.toString())
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)

        Toast.makeText(this, "Login failed: ${error?.message}", Toast.LENGTH_LONG).show()
    }
}