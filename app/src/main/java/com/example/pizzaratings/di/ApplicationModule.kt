package com.example.pizzaratings.di

import android.content.Context
import com.example.pizzaratings.utils.PizzaRatingsPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object ApplicationModule {

  @Provides
  fun providePizzaRatingsPreferences(@ApplicationContext appContext: Context) =
    PizzaRatingsPreferences(appContext)
}