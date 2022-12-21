package test.interview.thoughtctl.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import test.interview.thoughtctl.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //View binding
    private var activityMainBinding: ActivityMainBinding? = null

    //Adapter for recycler view
    private var imgurAdapter: ImgurGalleryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding!!.root)

        setView()
    }

    override fun onStart() {
        super.onStart()

        imgurAdapter = ImgurGalleryAdapter()

        activityMainBinding!!.changeLayout.setOnCheckedChangeListener { _, switchStatus ->
            changeLayout(switchStatus)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        //Clearing resources
        activityMainBinding = null
        imgurAdapter = null
    }

    private fun setView() {
       activityMainBinding?.imgurGalleryList!!.layoutManager = LinearLayoutManager(this)

    }

    /**
     * Called when switch's status is changed and layout is reassigned
    * */
    private fun changeLayout(switchStatus: Boolean) {
        activityMainBinding?.imgurGalleryList!!.layoutManager = if (switchStatus) {
            GridLayoutManager(this, 3)
        } else {
            LinearLayoutManager(this)
        }
        activityMainBinding!!.imgurGalleryList.adapter = imgurAdapter
    }



}