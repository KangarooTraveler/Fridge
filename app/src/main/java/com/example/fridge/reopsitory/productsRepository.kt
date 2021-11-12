package com.example.fridge.reopsitory

import androidx.lifecycle.LiveData
import com.example.fridge.data.ProductsDao
import com.example.fridge.modelF.ProductsF

class productsRepository(private val productsDao: ProductsDao) {
    val readAllDataP: LiveData<List<ProductsF>> = productsDao.readAllDataP()

    suspend fun addProducts(productsF: ProductsF){
        productsDao.addProducts(productsF)
    }

    suspend fun updateProducts(productsF: ProductsF){
        productsDao.UpdateProducts(productsF)
    }

    suspend fun deleteProduct(productsF: ProductsF){
        productsDao.deleteProduct(productsF)
    }

    suspend fun deleteAllProducts(){
        productsDao.deleteAllProducts()
    }

}