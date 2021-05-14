package bwp.pastebinclient.ui.details

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.forEach
import bwp.pastebinclient.Constants
import bwp.pastebinclient.R
import bwp.pastebinclient.databinding.ActivityPasteDetailsBinding
import bwp.pastebinclient.injector
import bwp.pastebinclient.model.PasteInfo
import com.google.firebase.analytics.FirebaseAnalytics
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

class PasteDetailsActivity : AppCompatActivity(), PasteDetailsScreen {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    @Inject
    lateinit var pasteDetailsPresenter: PasteDetailsPresenter

    private lateinit var binding: ActivityPasteDetailsBinding

    private var userKey: String? = null

    private var pasteInfo: PasteInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        binding = ActivityPasteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userKey = intent.getStringExtra(Constants.EXTRA_USER_KEY)
        pasteInfo = intent.getSerializableExtra(Constants.EXTRA_PASTE_INFO) as PasteInfo?

        val pasteInfo = this.pasteInfo
        if (pasteInfo != null) {
            supportActionBar?.title = pasteInfo.title

            binding.tvUrl.text = getString(R.string.url, pasteInfo.url)
            binding.tvDate.text = getString(R.string.published_at, DateFormat.getDateTimeInstance().format(Date(pasteInfo.date * 1000)))
            binding.tvExpireDate.text = getString(R.string.expires_at, if (pasteInfo.expireDate == 0L) "Never" else DateFormat.getDateTimeInstance().format(Date(pasteInfo.expireDate * 1000)))
            binding.tvPrivate.text = getString(R.string.privacy, pasteInfo.private.toString())
            binding.tvHitCount.text = pasteInfo.hits.toString()
            binding.tvFormat.text = pasteInfo.formatLong
            binding.tvSize.text = pasteInfo.size.toString()
        }
    }

    override fun onStart() {
        super.onStart()
        injector.inject(this)

        pasteDetailsPresenter.attachScreen(this)
        pasteDetailsPresenter.showPaste(pasteInfo?.key.orEmpty(), userKey)
    }

    override fun onStop() {
        pasteDetailsPresenter.detachScreen()
        super.onStop()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.forEach { item ->
            when (item.itemId) {
                R.id.menu_share, R.id.menu_copy -> {
                    item.isVisible = pasteInfo != null
                }
                R.id.menu_delete -> {
                    item.isVisible = pasteInfo != null && userKey != null
                }
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_share -> {
                onClickShareUrl()
                true
            }
            R.id.menu_copy -> {
                onClickCopyUrl()
                true
            }
            R.id.menu_delete -> {
                onClickDelete()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onClickShareUrl() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, pasteInfo?.url)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, null))
    }

    private fun onClickCopyUrl() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("paste url", pasteInfo?.url.orEmpty()))
    }

    private fun onClickDelete() {
        AlertDialog.Builder(this)
            .setMessage(R.string.delete_paste_question_message)
            .setPositiveButton(R.string.yes) { dialog, which ->
                pasteDetailsPresenter.deletePaste(pasteInfo?.key.orEmpty(), userKey.orEmpty())
            }
            .setNegativeButton(R.string.no) { dialog, which ->

            }.show()
    }

    override fun showPaste(rawPaste: String) {
        binding.tvCode.text = rawPaste
    }

    override fun showNetworkError(errorMsg: String) {
        Toast.makeText(this, "Network error: $errorMsg", Toast.LENGTH_LONG).show()
    }
}