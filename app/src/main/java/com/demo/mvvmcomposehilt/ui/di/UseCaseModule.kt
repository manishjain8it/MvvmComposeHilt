package com.demo.mvvmcomposehilt.ui.di

import com.demo.mvvmcomposehilt.domain.repository.CricketRepository
import com.demo.mvvmcomposehilt.domain.usecase.GetCurrentMatchUseCase
import com.demo.mvvmcomposehilt.domain.usecase.GetCurrentMatchUseCaseImpl
import com.demo.mvvmcomposehilt.domain.usecase.GetMatchInfoUseCase
import com.demo.mvvmcomposehilt.domain.usecase.GetMatchInfoUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideCurrentMatchesUseCase(repository: CricketRepository): GetCurrentMatchUseCase {
        return GetCurrentMatchUseCaseImpl(repository)
    }

    @Provides
    fun provideMatchInfoUseCase(repository: CricketRepository): GetMatchInfoUseCase {
        return GetMatchInfoUseCaseImpl(repository)
    }
}