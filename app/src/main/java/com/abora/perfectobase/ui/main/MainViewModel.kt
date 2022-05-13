package com.abora.perfectobase.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abora.perfectobase.base.BaseViewModel
import com.abora.perfectobase.data.models.CompetitionsData
import com.abora.perfectobase.data.remote.reporsitory.MainRepository
import kotlinx.coroutines.launch

class MainViewModel constructor(var mainRepository: MainRepository, val context: Context) :
    BaseViewModel() {


    //Responses
    var competitionsResponse = MutableLiveData<MutableList<CompetitionsData>>()


    fun getCompetitions() {
        loading.value = true
        viewModelScope.launch {
            mainRepository.getCompetitions(networkStatus, ).let {
                loading.value = false
                if (it.data != null) {
                    competitionsResponse.postValue(it.data.competitions.toMutableList())
                }
            }
        }
    }


}