package com.demo.mvvmcomposehilt.data.di

import com.demo.mvvmcomposehilt.data.repository.CricketRepositoryImpl
import com.demo.mvvmcomposehilt.domain.repository.CricketRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @ViewModelScoped
    @Binds
    fun provideCricketRepository(
        cricketRepositoryImpl: CricketRepositoryImpl
    ): CricketRepository
}