package com.sample.surveycomponent.di

import com.sample.surveycomponent.data.source.DefaultRepository
import com.sample.surveycomponent.data.source.RemoteDataSource
import com.sample.surveycomponent.data.source.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        ioDispatcher: CoroutineDispatcher
    ): Repository {
        return DefaultRepository(
            remoteDataSource, ioDispatcher
        )
    }
}