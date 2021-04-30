package bwp.pastebinclient.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bwp.pastebinclient.databinding.PasteListItemBinding
import bwp.pastebinclient.model.PasteInfo
import java.text.DateFormat
import java.util.*

class PastesListAdapter(private val onClickItem: (PasteInfo) -> Unit) : ListAdapter<PasteInfo, PastesListAdapter.ViewHolder>(PasteInfoDiffCallback) {

    class ViewHolder(private val binding: PasteListItemBinding, private val onClick: (PasteInfo) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        private var currentPasteInfo: PasteInfo? = null

        init {
            itemView.setOnClickListener {
                currentPasteInfo?.let { onClick(it) }
            }
        }

        fun bind(pasteInfo: PasteInfo) {
            currentPasteInfo = pasteInfo

            binding.tvPasteTitle.text = pasteInfo.title
            binding.tvCreatedAt.text = DateFormat.getDateTimeInstance().format(Date(pasteInfo.date * 1000))
            binding.tvExpiresIn.text = "Expires at ${DateFormat.getDateTimeInstance().format(Date(pasteInfo.expireDate * 1000))}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PasteListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClickItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pasteInfo = getItem(position)
        holder.bind(pasteInfo)
    }
}

object PasteInfoDiffCallback : DiffUtil.ItemCallback<PasteInfo>() {
    override fun areItemsTheSame(oldItem: PasteInfo, newItem: PasteInfo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PasteInfo, newItem: PasteInfo): Boolean {
        return oldItem == newItem
//        return oldItem.key == newItem.key
//                && oldItem.url == newItem.url
//                && oldItem.title == newItem.title
//                && oldItem.date == newItem.date
//                && oldItem.expireDate == newItem.expireDate
//                && oldItem.private == newItem.private
//                && oldItem.size == newItem.size
//                && oldItem.formatLong == newItem.formatLong
//                && oldItem.formatShort == newItem.formatShort
//                && oldItem.hits == newItem.hits
    }
}