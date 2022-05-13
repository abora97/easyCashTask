package com.abora.perfectobase.data.remote

import com.abora.perfectobase.data.models.CompetitionsDataModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET


interface RetrofitApi {


    @GET("competitions")
    fun getCompetitions(): Deferred<Response<CompetitionsDataModel>>


}