package com.example.fridge.reopsitory


import androidx.lifecycle.LiveData
import com.example.fridge.data.ReceptedDao
import com.example.fridge.modelF.ReceptedF
import kotlinx.coroutines.flow.Flow

class receptedRepository(private  val receptedDao: ReceptedDao) {

    val readAllData: LiveData<List<ReceptedF>> = receptedDao.readAllData()

    //================== Это все равно, что insertData ==================
   suspend fun addRecepted(receptedF: ReceptedF){
       receptedDao.addRecept(receptedF)
   }
    //================== Это все равно, что insertData ==================

    fun searchDatabase(searchQuery: String): Flow<List<ReceptedF>> {
        return receptedDao.searchDatabase(searchQuery)
    }

    suspend fun updateRecepted(receptedF: ReceptedF){
        receptedDao.updateRecapted(receptedF)
    }

    suspend fun deleteRecepted(receptedF: ReceptedF){
        receptedDao.deleteRecepted(receptedF)
    }

    suspend fun deleteAllRecepted(){
        receptedDao.deleteAllRecepted()
    }
}