package com.abora.perfectobase.data.models

import com.google.gson.annotations.SerializedName

data class DefaultDataModel(
    var code: Int,
    var status: Int,
    var data: DefaultData,
    var message: String
)

data class DefaultData(
    @SerializedName("user_id")
    var userId: Int,
    @SerializedName("verification_code")
    var verificationCode: Int,
    var token: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("is_success")
    var is_success: Boolean,
    @SerializedName("send_push")
    var pushStatus: Int,
    @SerializedName("is_verified")
    var is_verified: Boolean,
    @SerializedName("message")
    var message: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("body")
    var body: String
)

