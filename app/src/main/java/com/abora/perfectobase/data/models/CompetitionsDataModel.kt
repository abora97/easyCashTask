package com.abora.perfectobase.data.models

import com.google.gson.annotations.SerializedName

class CompetitionsDataModel(
    @SerializedName("count") val count: Int,
    @SerializedName("competitions") val competitions: List<CompetitionsData>
)



data class CompetitionsData (

    @SerializedName("id") val id : Int,
    @SerializedName("area") val area : Area,
    @SerializedName("name") val name : String,
    @SerializedName("code") val code : String,
    @SerializedName("emblemUrl") val emblemUrl : String,
    @SerializedName("plan") val plan : String,
    @SerializedName("currentSeason") val currentSeason : CurrentSeason,
    @SerializedName("numberOfAvailableSeasons") val numberOfAvailableSeasons : Int,
    @SerializedName("lastUpdated") val lastUpdated : String
)



data class CurrentSeason(

    @SerializedName("id") val id: Int,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("endDate") val endDate: String,
    @SerializedName("currentMatchday") val currentMatchday: Int,
    @SerializedName("winner") val winner: Winner
)


data class Winner(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("shortName") val shortName : String,
    @SerializedName("tla") val tla : String,
    @SerializedName("crestUrl") val crestUrl : String,
)

data class Area (

    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("countryCode") val countryCode : String,
    @SerializedName("ensignUrl") val ensignUrl : String
)