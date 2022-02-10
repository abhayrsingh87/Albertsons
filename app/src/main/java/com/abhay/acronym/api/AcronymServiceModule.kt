package com.abhay.acronym.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AcronymServiceModule {

    companion object {
        private const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/"
        val apiModule = module {

            fun provideHttpClient(): OkHttpClient {
                val logger =
                    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
                return OkHttpClient.Builder().addInterceptor(logger).build()
            }

            fun provideRetrofit(client: OkHttpClient): Retrofit {
                return Retrofit.Builder().baseUrl(BASE_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }

            fun provideAcronymService(retrofit: Retrofit): AcronymService {
                return retrofit.create(AcronymService::class.java)
            }
            single { provideHttpClient() }
            single { provideRetrofit(get()) }
            single { provideAcronymService(get()) }
        }
    }
}