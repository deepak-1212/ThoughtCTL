package test.interview.thoughtctl.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import test.interview.thoughtctl.data.model.Data
import test.interview.thoughtctl.data.repository.ImgurGalleryRepository
import test.interview.thoughtctl.utils.BackgroundThread

class MainActivityViewModel(private val repository: ImgurGalleryRepository) : ViewModel() {

    private lateinit var job: Job

    private val _imgurModels = MutableLiveData<List<Data>>()
    val imgurModels: LiveData<List<Data>>
        get() = _imgurModels

    fun getImgurModels() {
        job = BackgroundThread.ioThenMain(
            { repository.getPostImages() },
            {
                _imgurModels.value = it?.data
            }
        )
    }

    override fun onCleared() {
        super.onCleared()

        if (::job.isInitialized) job.cancel()

    }

}