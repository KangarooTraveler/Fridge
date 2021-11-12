package com.example.fridge.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.fridge.data.receptedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, receptedDatabase::class.java, "recepted_database").build()

    @Singleton
    @Provides
    fun provideDao(database: receptedDatabase) = database.receptedDao()
}