package com.demo.mvvmcomposehilt.domain.usecase

import com.demo.mvvmcomposehilt.data.common.Resource
import com.demo.mvvmcomposehilt.domain.mapper.CurrentMatchMapper
import kotlinx.coroutines.flow.Flow

interface GetCurrentMatchUseCase {
    suspend fun execute(): Flow<Resource<List<CurrentMatchMapper>>>
}