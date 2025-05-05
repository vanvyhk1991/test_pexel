package com.app.testpexel.di

import com.app.testpexel.BuildConfig
import com.app.testpexel.data.remote.adapter.RxErrorHandlingCallAdapterFactory
import com.app.testpexel.data.remote.interceptor.InterceptorImpl
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.koin.dsl.single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 60L
private const val WRITE_TIMEOUT = 60L
private const val READ_TIMEOUT = 60L

val networkModule = module {

    single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }
    single { GsonBuilder().setLenient().create() }
    single { HttpLoggingInterceptor() }
    single<Interceptor> {InterceptorImpl()}

    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val interceptor: Interceptor = get()

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder().apply {
            cache(get())
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(interceptor)
        }.build()
    }

    single() {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory(RxJava3CallAdapterFactory.create()))
            .client(get())
            .build()
    }
}