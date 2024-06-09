package com.moviles.taller2_moviles.utils

object Constants {

    //api service
    const val BASE_URL = "http://192.168.31.58:3000/api/"


    //insertar una familia

    const val POST_FAMILY_PATH = "postfamily"

    const val FAMILY_ID = "_id"
    const val FAMILY_NAME = "familyName"
    const val LOCATION = "location"
    const val GEOGRAPHIC_LOCATION = "geographicLocation"
    const val HOUSE_TYPE = "houseType"
    const val RISK = "risk"
    const val FAMILY_MEMBERS = "familymembers"
    const val MEMBER_ID = "_id"
    const val DUI = "DUI"
    const val FULL_NAME = "fullName"
    const val DATE_BIRTH = "dateBirth"
    const val CAN_READ_WRITE = "canReadWrite"
    const val SCHOLARITY = "scholarity"
    const val POSTMODEL = "Post"

    //get  familia

    const val GET_FAMILY_PATH = "getfamily"

    //API response

    const val RESPONSE_SUCCESSFUL = "result"
    const val RESPONSE_ERROR = "message"

}