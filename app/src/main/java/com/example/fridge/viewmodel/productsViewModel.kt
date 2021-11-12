package com.example.fridge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.fridge.data.ProductsDao
import com.example.fridge.data.ProductsDatabase
import com.example.fridge.modelF.ProductsF
import com.example.fridge.reopsitory.productsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class productsViewModel(application: Application): AndroidViewModel(application) {

    val readAllDataP: LiveData<List<ProductsF>>
    private val repository: productsRepository

    init {
        val productsDao = ProductsDatabase.getDatabase(application).productsDao()
        repository = productsRepository(productsDao)
        readAllDataP = repository.readAllDataP
    }

    fun addProducts(productsF: ProductsF){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProducts(productsF)
        }
    }

    fun updateProducts(productsF: ProductsF){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProducts(productsF)
        }
    }

    fun deleteProduct(productsF: ProductsF){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProduct(productsF)
        }
    }

    fun deleteAllProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllProducts()
        }
    }


}