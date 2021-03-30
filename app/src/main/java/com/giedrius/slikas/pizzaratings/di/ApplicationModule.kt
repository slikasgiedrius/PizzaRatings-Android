package com.giedrius.slikas.pizzaratings.di

import android.content.Context
import com.giedrius.slikas.pizzaratings.utils.PizzaRatingsPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

  @Provides
  @Singleton
  fun providePizzaRatingsPreferences(@ApplicationContext appContext: Context) =
    PizzaRatingsPreferences(appContext)
}