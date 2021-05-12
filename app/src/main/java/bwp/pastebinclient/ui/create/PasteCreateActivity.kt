package bwp.pastebinclient.ui.create

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bwp.pastebinclient.databinding.ActivityPasteCreateBinding

class PasteCreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasteCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasteCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}