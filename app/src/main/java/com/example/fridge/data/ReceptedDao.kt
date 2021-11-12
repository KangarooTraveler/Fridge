package com.example.fridge.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.navigation.Navigator
import androidx.room.*
import androidx.room.Query
import com.example.fridge.modelF.ReceptedF
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceptedDao {

    //В случае чего изменить значение на IGNORE (OnConfictStrategy.IGNORE) или наоборот на REPLACE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addRecept(receptedF: ReceptedF)

    //В случае чего изменить значение LiveData на Flow или наоборот
    @Query("SELECT * FROM recepted_table WHERE Opisanie LIKE :searchQuery"  )
    fun searchDatabase(searchQuery: String): Flow<List<ReceptedF>>

    @Update
    suspend fun updateRecapted(receptedF: ReceptedF)

    @Delete
    suspend fun deleteRecepted(receptedF: ReceptedF)

    @Query("DELETE FROM recepted_table")
    suspend fun  deleteAllRecepted()

    //В случае чего изменить значение функции на Flow заместо LiveData или наоборот
    @Query("SELECT * FROM recepted_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<ReceptedF>>
}