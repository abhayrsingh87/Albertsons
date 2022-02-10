package com.abhay.acronym

import android.app.Application
import com.abhay.acronym.api.AcronymServiceModule
import com.abhay.acronym.repository.AcronymRepository
import com.abhay.acronym.repository.AcronymRepositoryImplementation
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AcronymApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val appModule = module {
            single<AcronymRepository> { AcronymRepositoryImplementation() }
        }
        startKoin {
            modules(listOf(appModule, AcronymServiceModule.apiModule))
        }
    }
}