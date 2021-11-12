package com.example.fridge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import androidx.room.Query
import com.example.fridge.data.receptedDatabase
import com.example.fridge.reopsitory.receptedRepository
import com.example.fridge.modelF.ReceptedF
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class receptedViewModel(application: Application):AndroidViewModel(application) {

    val readAllData: LiveData<List<ReceptedF>>

    private val repository: receptedRepository
       init{
           val receptedDao = receptedDatabase.getDatabase(
               application
           ).receptedDao()
           repository =
               receptedRepository(receptedDao)
           readAllData = repository.readAllData
       }

    fun addRecepted(receptedF: ReceptedF){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRecepted(receptedF)
        }

    }
    fun updateRecepted(receptedF: ReceptedF){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRecepted(receptedF)
        }
    }

    fun deleteRecepted(receptedF: ReceptedF){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRecepted(receptedF)
        }
    }

    fun deleteAllRecepted(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllRecepted()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<ReceptedF>>{
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}