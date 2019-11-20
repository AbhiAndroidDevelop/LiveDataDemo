package com.example.livedatademo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.livedatademo.R
import com.example.livedatademo.model.Product
import com.example.livedatademo.view_model.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var model : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        observeProgress()
        subscribeProductList()
    }

    private fun observeProgress() {
       model.isUpdating.observe(this,Observer<Boolean>{isUpdating ->

           if (isUpdating)
           {
               progressBar.visibility = View.VISIBLE
           }
           else{
               progressBar.visibility = View.GONE
           }
       })
    }


    private fun subscribeProductList() {


        model.data.observe(this,Observer<List<Product>>{productList ->
            runOnUiThread {
                textView.text = productList.toString()
            }
        })

        model.subscribe(this)
    }
}
