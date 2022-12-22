package test.interview.thoughtctl.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import test.interview.thoughtctl.constants.GRID_TYPE
import test.interview.thoughtctl.constants.LIST_TYPE
import test.interview.thoughtctl.data.model.Data
import test.interview.thoughtctl.databinding.ItemSingleGridBinding
import test.interview.thoughtctl.databinding.ItemSingleLinearBinding
import test.interview.thoughtctl.utils.convertToDateTimeFormat
import test.interview.thoughtctl.utils.glideLoader

private const val TAG = "ImgurGalleryAdapter"

class ImgurGalleryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemList = ArrayList<Any>()
    private var viewType = 0

    class ImgurViewHolderGrid(private val itemSingleGridBinding: ItemSingleGridBinding) :
        RecyclerView.ViewHolder(itemSingleGridBinding.root) {

        fun bindView(data: Data) {
            with(data) {
                itemSingleGridBinding.itemTitle.text = title
                if (images_count > 1) {
                    itemSingleGridBinding.counterIcon.visibility = View.VISIBLE
                    itemSingleGridBinding.counterIcon.text = String.format("+%d", images_count - 1)
                } else {
                    itemSingleGridBinding.counterIcon.visibility = View.GONE
                }
                itemSingleGridBinding.itemPhoto.glideLoader(images[0].link)
                itemSingleGridBinding.itemDate.text = datetime.convertToDateTimeFormat()
            }
        }
    }

    class ImgurViewHolderLinear(private val itemSingleLinearBinding: ItemSingleLinearBinding) :
        RecyclerView.ViewHolder(itemSingleLinearBinding.root) {

        fun bindView(data: Data) {
            with(data) {
                itemSingleLinearBinding.itemTitle.text = title
                itemSingleLinearBinding.itemPhoto.glideLoader(images[0].link)
                if (images_count > 1) {
                    itemSingleLinearBinding.itemAdditionalImages.visibility = View.VISIBLE
                    itemSingleLinearBinding.itemAdditionalImages.text = String.format("Additional Images: %d", images_count - 1)
                } else {
                    itemSingleLinearBinding.itemAdditionalImages.visibility = View.GONE
                }
                itemSingleLinearBinding.itemDate.text = datetime.convertToDateTimeFormat()
            }
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
        when (viewType) {
            LIST_TYPE -> {
                (holder as ImgurViewHolderLinear).bindView(itemList[holder.adapterPosition] as Data)
            }
            GRID_TYPE -> {
                (holder as ImgurViewHolderGrid).bindView(itemList[holder.adapterPosition] as Data)
            }
        }

    }

    override fun getItemCount() = itemList.size

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    fun setList(list: List<Any>) {
        CoroutineScope(Main).launch {
            notifyItemRangeRemoved(0, itemCount)
            itemList.clear()
            itemList.addAll(list)
            notifyItemRangeInserted(0, itemList.size)
        }
    }

    fun setViewType(type: Int) {
        viewType = type
    }

}