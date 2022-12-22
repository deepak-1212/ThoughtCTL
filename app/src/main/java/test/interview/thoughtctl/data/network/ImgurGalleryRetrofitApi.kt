package test.interview.thoughtctl.data.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import test.interview.thoughtctl.constants.NetworkConstants
import test.interview.thoughtctl.data.model.ImgurGalleryModel

interface ImgurGalleryRetrofitApi {

    @GET("week")
    suspend fun getPosts(
        @HeaderMap mapData: Map<String, String>
    ): Response<ImgurGalleryModel>

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