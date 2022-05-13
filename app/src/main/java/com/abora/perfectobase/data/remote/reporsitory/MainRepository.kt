package com.abora.perfectobase.data.remote.reporsitory

import com.abora.perfectobase.data.remote.RetrofitApi
import com.abora.perfectobase.data.remote.networkHandling.NetworkResult
import com.abora.perfectobase.data.remote.networkHandling.NetworkStatus


class MainRepository constructor(var apiService: RetrofitApi) : NetworkResult() {

    suspend fun getCompetitions(networkStatus: NetworkStatus) = getResult({
        apiService.getCompetitions().await()
    }, networkStatus)


}