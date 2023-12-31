package com.storage.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabaseDatabase(
        @ApplicationContext context: Context,
    ): NachChallangeDatabase = Room.databaseBuilder(
        context,
        NachChallangeDatabase::class.java,
        "nach-challenge-database",
    ).build()
}