package com.demo.mvvmcomposehilt.data.repository

import com.demo.mvvmcomposehilt.data.common.Resource
import com.demo.mvvmcomposehilt.data.datasource.RemoteDataSource
import com.demo.mvvmcomposehilt.domain.mapper.CurrentMatchMapper
import com.demo.mvvmcomposehilt.domain.mapper.mapCurrentMatchMapper
import com.demo.mvvmcomposehilt.domain.mapper.mapToMatchMapper
import com.demo.mvvmcomposehilt.domain.repository.CricketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CricketRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CricketRepository {
    override suspend fun getCurrentMatches(): Flow<Resource<List<CurrentMatchMapper>>> {
        return flow {
            remoteDataSource.getCurrentMatches(offset = 0).run {
                when (this) {
                    is Resource.Loading -> emit(Resource.Loading)
                    is Resource.Success -> emit(Resource.Success(response.data.mapCurrentMatchMapper()))
                    is Resource.Error -> emit(Resource.Error(exception))
                }
            }
        }
    }

    override suspend fun getMatchInfo(matchId: String): Flow<Resource<CurrentMatchMapper>> {
        return flow {
            remoteDataSource.getMatchInfo(offset = 0, matchId).run {
                when (this) {
                    is Resource.Loading -> emit(Resource.Loading)
                    is Resource.Success -> emit(Resource.Success(mapToMatchMapper(response.data)))
                    is Resource.Error -> emit(Resource.Error(exception))
                }
            }
        }
    }
}