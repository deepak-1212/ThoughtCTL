package test.interview.thoughtctl.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import test.interview.thoughtctl.R
import test.interview.thoughtctl.constants.GRID_TYPE
import test.interview.thoughtctl.constants.LIST_TYPE
import test.interview.thoughtctl.data.model.Data
import test.interview.thoughtctl.data.network.ImgurGalleryRetrofitApi
import test.interview.thoughtctl.data.repository.ImgurGalleryRepository
import test.interview.thoughtctl.databinding.ActivityMainBinding
import test.interview.thoughtctl.viewmodel.MainActivityViewModel
import test.interview.thoughtctl.viewmodel.ViewModelFactory

private val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    //View binding
    private var activityMainBinding: ActivityMainBinding? = null

    //Adapter for recycler view
    private var imgurAdapter: ImgurGalleryAdapter? = null

    //Initialize view model and factory
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var menuHost: MenuHost

    private lateinit var dataList: List<Data>

    private var menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            // Add menu items here
            menuInflater.inflate(R.menu.home_menu, menu)

            val searchView = menu.findItem(R.id.searchFromList).actionView as SearchView
            searchView.queryHint = getString(R.string.search)
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (dataList.isNotEmpty()) {
                        if (newText != null) {
                            val temp = dataList.filter { data -> data.title.lowercase().contains(newText.lowercase()) }
                            imgurAdapter!!.setList(temp)
                        } else {
                            imgurAdapter!!.setList(dataList)
                        }
                    }
                    return true
                }

            })


        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            // Handle the menu selection
            when (menuItem.itemId) {
                R.id.searchFromList -> {

                }
            }
            return true
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding!!.root)

        //Initialize
        menuHost = this


        val api = ImgurGalleryRetrofitApi()
        val imgurGalleryRepository = ImgurGalleryRepository(api)
        viewModelFactory = ViewModelFactory(imgurGalleryRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]

    }

    override fun onResume() {
        super.onResume()
        menuHost.addMenuProvider(
            menuProvider,
            this,
            Lifecycle.State.RESUMED
        )
    }

    override fun onStop() {
        super.onStop()
        menuHost.removeMenuProvider(menuProvider)
    }

    override fun onStart() {
        super.onStart()

        imgurAdapter = ImgurGalleryAdapter()

        //Start with Linear layout
        changeLayout(false)

        activityMainBinding!!.changeLayout.setOnCheckedChangeListener { _, switchStatus ->
            changeLayout(switchStatus)
        }

        //Add observer to fetch changes in list
        viewModel.imgurModels.observe(this) {
            if (it != null) {
                dataList = it.filter { data ->
                    data.images_count > 0
                }.sortedByDescending { data -> data.datetime }
                imgurAdapter!!.setList(dataList)
            }
        }

        viewModel.getImgurModels()


    }

    override fun onDestroy() {
        super.onDestroy()

        //Clearing resources
        activityMainBinding = null
        imgurAdapter = null
    }

    /**
     * Called when switch's status is changed and layout is reassigned
     * */
    private fun changeLayout(switchStatus: Boolean) {
        activityMainBinding?.imgurGalleryList!!.layoutManager = if (switchStatus) {

            imgurAdapter!!.setViewType(GRID_TYPE)
            GridLayoutManager(this, 2)

        } else {
            imgurAdapter!!.setViewType(LIST_TYPE)
            LinearLayoutManager(this)
        }
        activityMainBinding!!.imgurGalleryList.adapter = imgurAdapter
    }


}