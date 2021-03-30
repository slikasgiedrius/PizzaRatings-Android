package com.giedrius.slikas.pizzaratings.di

import com.giedrius.slikas.pizzaratings.BuildConfig
import com.giedrius.slikas.pizzaratings.data.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

  @Provides
  fun provideBaseUrl() = BuildConfig.BASE_URL

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .build()
  } else OkHttpClient
    .Builder()
    .build()

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
    Retrofit.Builder()
      .addConverterFactory(MoshiConverterFactory.create())
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .build()


  @Provides
  @Singleton
  fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)
}