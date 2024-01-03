package com.example.locationremote

import android.app.Application
import android.content.Context
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationRemoteModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
    @Provides
    fun provideBooksRef(firestore: FirebaseFirestore) = firestore.collection("location")

    @Provides
    @Singleton
    fun provideContextRemote(application: Application): Context {
        return application.applicationContext
    }
    @Provides
    @Singleton
    fun providerLocationRemoteData(locationRef: CollectionReference) : LocationApi{
        return LocationRemoteImp(locationRef)
    }




}