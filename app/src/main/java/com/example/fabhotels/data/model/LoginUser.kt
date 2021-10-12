package com.example.fabhotels.data.model


import com.google.gson.annotations.SerializedName

data class LoginUser(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("token")
    val token: String
)