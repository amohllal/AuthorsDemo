package com.assignment.data.di

import com.assignment.data.repository.AuthorsRepositoryImpl
import com.assignment.data.repository.PostsRepositoryImpl
import com.assignment.domain.repository.AuthorsRepository
import com.assignment.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideAuthorsRepository(authorsRepositoryImpl: AuthorsRepositoryImpl): AuthorsRepository

    @Binds
    @Singleton
    abstract fun providePostsRepository(postsRepositoryImpl: PostsRepositoryImpl): PostsRepository


}