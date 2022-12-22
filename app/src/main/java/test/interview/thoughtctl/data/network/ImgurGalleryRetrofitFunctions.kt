package test.interview.thoughtctl.data.network

import android.util.Log

private val TAG = "ImgurGallery"

object ImgurGalleryRetrofitFunctions {

    fun getPosts() {
        Log.i(TAG, "getPosts: Called")

        val header = HashMap<String, String>()
        header["Authorization"] = "Client-ID 3db2844137aa844"

        /*ImgurGalleryRetrofitApi().getPosts(header).enqueue(object : Callback<ImgurGalleryModel> {
            override fun onResponse(
                call: Call<ImgurGalleryModel>,
                response: Response<ImgurGalleryModel>
            ) {
                Log.i(TAG, "onResponse: ${response.body()!!.data}")
            }

            override fun onFailure(call: Call<ImgurGalleryModel>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.cause}")
            }
        })*/
    }

}