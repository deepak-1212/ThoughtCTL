package test.interview.thoughtctl.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import test.interview.thoughtctl.data.repository.ImgurGalleryRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: ImgurGalleryRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repository) as T
    }

}