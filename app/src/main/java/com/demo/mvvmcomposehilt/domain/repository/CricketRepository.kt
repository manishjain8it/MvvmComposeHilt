package com.demo.mvvmcomposehilt.domain.repository

import com.demo.mvvmcomposehilt.data.common.Resource
import com.demo.mvvmcomposehilt.domain.mapper.CurrentMatchMapper
import kotlinx.coroutines.flow.Flow

interface CricketRepository {

    suspend fun getCurrentMatches(): Flow<Resource<List<CurrentMatchMapper>>>

    suspend fun getMatchInfo(matchId: String): Flow<Resource<CurrentMatchMapper>>
}