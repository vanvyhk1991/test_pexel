package com.app.testpexel.di

import com.app.testpexel.data.remote.repository.IRemoteRepository
import com.app.testpexel.data.remote.repository.RemoteRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IRemoteRepository> { RemoteRepository(get()) }
}