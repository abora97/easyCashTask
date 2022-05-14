package com.abora.perfectobase.data.models

import com.google.gson.annotations.SerializedName

class CompetitionsDetailsDataModel(
    @SerializedName("id") val id : Int,
    @SerializedName("area") val area : Area,
    @SerializedName("name") val name : String,
    @SerializedName("code") val code : String,
    @SerializedName("emblemUrl") val emblemUrl : String,
    @SerializedName("plan") val plan : String,
    @SerializedName("currentSeason") val currentSeason : CurrentSeason,
    @SerializedName("seasons") val seasons : List<Seasons>,
    @SerializedName("lastUpdated") val lastUpdated : String
)




data class Seasons (

    @SerializedName("id") val id : Int,
    @SerializedName("startDate") val startDate : String,
    @SerializedName("endDate") val endDate : String,
    @SerializedName("currentMatchday") val currentMatchday : Int,
    @SerializedName("winner") val winner : Winner
)