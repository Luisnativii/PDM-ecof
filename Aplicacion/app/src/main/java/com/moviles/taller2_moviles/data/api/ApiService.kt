package com.moviles.taller2_moviles.data.api

import com.moviles.taller2_moviles.utils.Constants
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    //post family


    @Headers(value = ["Content-Type: application/json"])
    @POST(value = Constants.POST_FAMILY_PATH)
    suspend fun insertFamilyAPI(@Body family : FamilyApi) : ApiResponseSuccessful

   // @Headers(value = ["Content-Type: application/json"])
    //@POST(value = Constants.API_PATH + Constants.POST_COURSE_PATH)
    //suspend fun insertCourse(@Body course : CourseApi) : ApiResponseSuccessful

}