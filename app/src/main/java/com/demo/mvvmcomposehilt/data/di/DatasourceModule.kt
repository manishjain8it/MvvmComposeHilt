package com.demo.mvvmcomposehilt.data.di

import com.demo.mvvmcomposehilt.data.datasource.RemoteDataSource
import com.demo.mvvmcomposehilt.data.datasource.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatasourceModule {
    @Singleton
    @Binds
    fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}