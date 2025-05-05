package com.app.testpexel.di

import com.app.testpexel.data.remote.api.RemoteApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = true) {
        get<Retrofit>().create(RemoteApi::class.java)
    }
}