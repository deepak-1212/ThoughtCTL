package test.interview.thoughtctl.data.repository

import test.interview.thoughtctl.data.network.ImgurGalleryRetrofitApi

class ImgurGalleryRepository(private val imgurGalleryRetrofitApi: ImgurGalleryRetrofitApi) :
    SafeApiRequest() {

    suspend fun getPostImages() = apiRequest {
        val header = HashMap<String, String>()
        header["Authorization"] = "Client-ID 3db2844137aa844"

        imgurGalleryRetrofitApi.getPosts(header)
    }
}