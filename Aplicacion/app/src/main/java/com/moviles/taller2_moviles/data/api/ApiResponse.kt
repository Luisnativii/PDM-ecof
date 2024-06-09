package com.moviles.taller2_moviles.data.api

import com.google.gson.annotations.SerializedName
import com.moviles.taller2_moviles.utils.Constants

data class ApiResponseSuccessful(
    @SerializedName(value = Constants.RESPONSE_SUCCESSFUL)
    val result : String

)

data class ApiResponseError(

    @SerializedName(value = Constants.RESPONSE_ERROR)
    val message: String
)