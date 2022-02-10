package com.abhay.acronym.repository

import com.abhay.acronym.model.Meanings
import com.abhay.acronym.api.AcronymService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

class AcronymRepositoryImplementation : AcronymRepository, KoinComponent {

    private val service: AcronymService by inject()

    override suspend fun getAcronyms(shortForm: String): Response<List<Meanings>> =
        service.getAcronymsList(shortForm)
}