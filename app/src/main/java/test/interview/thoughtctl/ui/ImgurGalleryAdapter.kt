package test.interview.thoughtctl.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import test.interview.thoughtctl.constants.GRID_TYPE
import test.interview.thoughtctl.constants.LIST_TYPE
import test.interview.thoughtctl.databinding.ItemSingleGridBinding
import test.interview.thoughtctl.databinding.ItemSingleLinearBinding

class ImgurGalleryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemList = ArrayList<Any>()
    private var viewType = 0

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

        return when (viewType) {
            LIST_TYPE -> {
                ImgurViewHolderLinear(
                    ItemSingleLinearBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                ImgurViewHolderGrid(
                    ItemSingleGridBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(holder.adapterPosition)) {
            LIST_TYPE -> {
                (holder as ImgurViewHolderLinear).bindView()
            }
            GRID_TYPE -> {
                (holder as ImgurViewHolderGrid).bindView()
            }
        }

    }

    override fun getItemCount() = itemList.size

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    private fun setList(list: ArrayList<Any>) {
        CoroutineScope(Main).launch {
            notifyItemRangeRemoved(0, itemCount)
            itemList.clear()
            itemList.addAll(list)
            notifyItemRangeInserted(0, itemList.size)
        }
    }

    private fun setViewType(type: Int) {
        viewType = type
    }

}