package com.abora.perfectobase.data.remote

import com.abora.perfectobase.data.models.CompetitionsDataModel
import com.abora.perfectobase.data.models.CompetitionsDetailsDataModel
import com.abora.perfectobase.data.models.TeamDetailsDataModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface RetrofitApi {


    @GET("competitions")
    fun getCompetitions(): Deferred<Response<CompetitionsDataModel>>

    @GET("competitions/{id}")
    fun getCompetitionsDetails(@Path("id") id: String): Deferred<Response<CompetitionsDetailsDataModel>>

    @GET("competitions/{id}/teams")
    fun getTeamDetails(@Path("id") id: String): Deferred<Response<TeamDetailsDataModel>>


}