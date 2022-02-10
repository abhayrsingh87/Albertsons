package com.abhay.acronym.api

import com.abhay.acronym.model.Meanings
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {

    @GET("dictionary.py")
    suspend fun getAcronymsList(@Query("sf") sf: String): Response<List<Meanings>>
}