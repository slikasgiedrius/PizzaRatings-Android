package com.giedrius.slikas.pizzaratings.di

import android.content.Context
import com.giedrius.slikas.pizzaratings.utils.PizzaRatingsPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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

  @Provides
  @Singleton
  fun provideFirestore() = Firebase.firestore

  @Provides
  @Singleton
  fun provideFirebaseAuth() = Firebase.auth
}