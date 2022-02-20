package com.assignment.data.di

import android.content.Context
import androidx.room.Room
import com.assignment.data.local.AuthorDAO
import com.assignment.data.local.AuthorDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    fun provideAuthorDao(appDatabase: AuthorDataBase): AuthorDAO {
        return appDatabase.getAuthorDao()
    }

    @Provides
    @Singleton
    fun provideAuthorDatabase(@ApplicationContext appContext: Context)
            : AuthorDataBase {
        return Room.databaseBuilder(
            appContext,
            AuthorDataBase::class.java,
            "Author_Database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}