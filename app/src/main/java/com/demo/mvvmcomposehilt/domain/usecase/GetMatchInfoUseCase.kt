package com.demo.mvvmcomposehilt.domain.usecase

import com.demo.mvvmcomposehilt.data.common.Resource
import com.demo.mvvmcomposehilt.domain.mapper.CurrentMatchMapper
import kotlinx.coroutines.flow.Flow

interface GetMatchInfoUseCase {
    suspend fun execute(matchId: String): Flow<Resource<CurrentMatchMapper>>
}