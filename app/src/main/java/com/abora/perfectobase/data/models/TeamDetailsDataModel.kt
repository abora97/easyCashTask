package com.abora.perfectobase.data.models

import com.google.gson.annotations.SerializedName

class TeamDetailsDataModel(
    @SerializedName("competition") val competition : Competition,
    @SerializedName("season") val season : Seasons,
    @SerializedName("teams") val teams : List<Teams>
)


data class Teams (

    @SerializedName("id") val id : Int,
    @SerializedName("area") val area : Area,
    @SerializedName("name") val name : String,
    @SerializedName("shortName") val shortName : String,
    @SerializedName("tla") val tla : String,
    @SerializedName("crestUrl") val crestUrl : String,
    @SerializedName("address") val address : String,
    @SerializedName("phone") val phone : String,
    @SerializedName("website") val website : String,
    @SerializedName("email") val email : String,
    @SerializedName("founded") val founded : Int,
    @SerializedName("clubColors") val clubColors : String,
    @SerializedName("venue") val venue : String,
    @SerializedName("lastUpdated") val lastUpdated : String
)


data class Competition (

    @SerializedName("id") val id : Int,
    @SerializedName("area") val area : Area,
    @SerializedName("name") val name : String,
    @SerializedName("code") val code : String,
    @SerializedName("plan") val plan : String,
    @SerializedName("lastUpdated") val lastUpdated : String
)