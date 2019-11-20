package com.example.livedatademo.view_model

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.livedatademo.model.Product
import kotlin.concurrent.thread

class MainActivityViewModel :ViewModel() {

    val data : MutableLiveData<List<Product>> = MutableLiveData()
    val isUpdating : MutableLiveData<Boolean> = MutableLiveData()

    fun subscribe(activity : Activity){
        isUpdating.postValue(true)

        thread {
            Thread.sleep(3000)
            val productList : MutableList<Product> = ArrayList()
            productList.add(Product("HP"))
            productList.add(Product("Dell"))
            productList.add(Product("Acer"))
            productList.add(Product("Motorola"))
            productList.add(Product("Samsung"))

            activity.runOnUiThread {
                data.postValue(productList)
                isUpdating.postValue(false)
            }

        }

    }

}