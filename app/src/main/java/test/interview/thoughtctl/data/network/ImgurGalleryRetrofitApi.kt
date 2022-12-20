package test.interview.thoughtctl.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import test.interview.thoughtctl.constants.NetworkConstants

interface ImgurGalleryRetrofitApi {

    @POST("")
    fun getSearchedPosts(text: String)

    companion object {
        operator fun invoke(): ImgurGalleryRetrofitApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkConstants.baseUrl)
                .build()
                .create(ImgurGalleryRetrofitApi::class.java)
        }
    }

}