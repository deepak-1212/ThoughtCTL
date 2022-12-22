package test.interview.thoughtctl.data.model

data class Data(
    val account_id: Int,
    val account_url: String,
    val animated: Boolean,
    val bandwidth: Long,
    val datetime: Long,
    val description: String,
    val height: Int,
    val id: String,
    val images: List<Image>,
    val images_count: Int,
    val in_gallery: Boolean,
    val layout: String,
    val title: String,
    val topic: Any,
    val topic_id: Int,
    val type: String,
    val width: Int
)