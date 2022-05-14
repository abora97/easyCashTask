package com.abora.perfectobase.data.remote.reporsitory

import com.abora.perfectobase.data.remote.RetrofitApi
import com.abora.perfectobase.data.remote.networkHandling.NetworkResult
import com.abora.perfectobase.data.remote.networkHandling.NetworkStatus


class MainRepository constructor(var apiService: RetrofitApi) : NetworkResult() {

    suspend fun getCompetitions(networkStatus: NetworkStatus) = getResult({
        apiService.getCompetitions().await()
    }, networkStatus)


    suspend fun getCompetitionsDetails(networkStatus: NetworkStatus,id:String) = getResult({
        apiService.getCompetitionsDetails(id=id).await()
    }, networkStatus)

    suspend fun getTeamDetails(networkStatus: NetworkStatus,id:String) = getResult({
        apiService.getTeamDetails(id=id).await()
    }, networkStatus)


}