package com.example.fridge.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fridge.modelF.ProductsF

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProducts(productsF: ProductsF)

    @Delete
    suspend fun deleteProduct(productsF: ProductsF)

    @Query("DELETE FROM products_table")
    suspend fun deleteAllProducts()

    @Update
    suspend fun UpdateProducts(productsF: ProductsF)

    @Query("SELECT * FROM products_table ORDER BY id ASC")
    fun readAllDataP(): LiveData<List<ProductsF>>
}