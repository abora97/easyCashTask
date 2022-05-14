package com.abora.perfectobase.ui.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abora.perfectobase.base.BaseViewModel
import com.abora.perfectobase.data.models.CompetitionsData
import com.abora.perfectobase.data.models.CompetitionsDetailsDataModel
import com.abora.perfectobase.data.models.TeamDetailsDataModel
import com.abora.perfectobase.data.remote.reporsitory.MainRepository
import kotlinx.coroutines.launch

class MainViewModel constructor(var mainRepository: MainRepository, val context: Context) :
    BaseViewModel() {


    //Responses
    var competitionsResponse = MutableLiveData<MutableList<CompetitionsData>>()
    var competitionsDetailsResponse = MutableLiveData<CompetitionsDetailsDataModel>()
    var teamDetailsResponse = MutableLiveData<TeamDetailsDataModel>()


    fun getCompetitions() {
        loading.value = true
        viewModelScope.launch {
            mainRepository.getCompetitions(networkStatus=networkStatus).let {
                loading.value = false
                if (it.data != null) {
                    competitionsResponse.postValue(it.data.competitions.toMutableList())
                }
            }
        }
    }


    fun getCompetitionsDetails(id:String) {
        loading.value = true
        viewModelScope.launch {
            mainRepository.getCompetitionsDetails(networkStatus=networkStatus, id=id).let {
                loading.value = false
                if (it.data != null) {
                    competitionsDetailsResponse.postValue(it.data)
                }
            }
        }
    }

    fun getTeamDetails(id:String) {
        loading.value = true
        viewModelScope.launch {
            mainRepository.getTeamDetails(networkStatus=networkStatus, id=id).let {
                loading.value = false
                if (it.data != null) {
                    teamDetailsResponse.postValue(it.data)
                }
            }
        }
    }


}