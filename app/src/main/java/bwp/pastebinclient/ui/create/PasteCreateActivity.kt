package bwp.pastebinclient.ui.create

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bwp.pastebinclient.Constants
import bwp.pastebinclient.R
import bwp.pastebinclient.databinding.ActivityPasteCreateBinding
import bwp.pastebinclient.injector
import javax.inject.Inject

class PasteCreateActivity : AppCompatActivity(), PasteCreateScreen {

    @Inject
    lateinit var pasteCreatePresenter: PasteCreatePresenter

    private var userKey: String? = null

    private lateinit var binding: ActivityPasteCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasteCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userKey = intent.getStringExtra(Constants.EXTRA_USER_KEY)

        binding.etPasteCode.requestFocus()
    }

    override fun onStart() {
        super.onStart()
        injector.inject(this)

        pasteCreatePresenter.attachScreen(this)
    }

    override fun onStop() {
        pasteCreatePresenter.detachScreen()
        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_publish -> {
                onClickPublish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onClickPublish() {
        var privacy = 0
        if (binding.rbPrivacyPrivate.isSelected) privacy = 2
        if (binding.rbPrivacyUnlisted.isSelected) privacy = 1
        if (binding.rbPrivacyPublic.isSelected) privacy = 0

        pasteCreatePresenter.createPaste(
            binding.etPasteCode.text.toString(),
            if (binding.etPasteTitle.text.isBlank()) null else binding.etPasteTitle.text.toString(),
            if (binding.etShortFormat.text.isBlank()) null else binding.etShortFormat.text.toString(),
            privacy,
            if (binding.etExpireString.text.isBlank()) null else binding.etExpireString.text.toString(),
            userKey
        )
    }

    override fun createSuccessful(pasteKey: String) {
        onBackPressed()
    }

    override fun createFailed(error: Throwable?) {
        Toast.makeText(this, "Could not create paste. Error: ${error?.message}", Toast.LENGTH_LONG).show()
    }
}