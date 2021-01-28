package com.chiper.testchiper.di

import com.chiper.testchiper.BuildConfig
import com.chiper.testchiper.api.MovieApi
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val okHttpClientWithoutInterceptor = "defaultOkHttpClient"
const val retrofitWithoutInterceptor = "retrofitWithoutInterceptor"
private const val DEFAULT_TIME_OUT = 60L

val networkModule = module {

    // Retrofit and OkHttpClient instances
    single(named(okHttpClientWithoutInterceptor)) { provideDefaultOkHttpClient() }
    single(named(retrofitWithoutInterceptor)) { provideRetrofitClient(get(named(okHttpClientWithoutInterceptor))) }
    // API
    single { provideMovieApi(get(named(retrofitWithoutInterceptor))) }
}

/**
 * OkHttpClient without Interceptor
 */
private fun provideDefaultOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        .build()
}

/**
 * Retrofit clients
 */
private fun provideRetrofitClient(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_MOVIES)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

private fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)
