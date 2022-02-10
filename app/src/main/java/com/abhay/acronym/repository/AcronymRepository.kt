package com.abhay.acronym.repository

import com.abhay.acronym.model.Meanings
import retrofit2.Response

interface AcronymRepository {
    suspend fun getAcronyms(shortForm: String): Response<List<Meanings>>
}