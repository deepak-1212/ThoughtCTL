package test.interview.thoughtctl.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import test.interview.thoughtctl.databinding.ActivityMainBinding
import test.interview.thoughtctl.databinding.ItemSingleGridBinding
import test.interview.thoughtctl.databinding.ItemSingleLinearBinding

class ImgurGalleryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemList = ArrayList<Any>()

    class ImgurViewHolderGrid(private val itemSingleGridBinding: ItemSingleGridBinding) :
        RecyclerView.ViewHolder(itemSingleGridBinding.root) {

        fun bindView() {

        }
    }

    class ImgurViewHolderLinear(private val itemSingleLinearBinding: ItemSingleLinearBinding) :
        RecyclerView.ViewHolder(itemSingleLinearBinding.root) {

        fun bindView() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImgurViewHolderGrid(
            ItemSingleGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ImgurViewHolderGrid).bindView()
    }

    override fun getItemCount() = itemList.size

    private fun setList(list: ArrayList<Any>) {
        CoroutineScope(Main).launch {
            notifyItemRangeRemoved(0, itemCount)
            itemList.clear()
            itemList.addAll(list)
            notifyItemRangeInserted(0, itemList.size)
        }
    }

}