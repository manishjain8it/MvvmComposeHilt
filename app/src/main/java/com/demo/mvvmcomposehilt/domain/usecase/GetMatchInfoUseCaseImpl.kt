package com.demo.mvvmcomposehilt.domain.usecase

import com.demo.mvvmcomposehilt.data.common.Resource
import com.demo.mvvmcomposehilt.data.common.onError
import com.demo.mvvmcomposehilt.data.common.onLoading
import com.demo.mvvmcomposehilt.data.common.onSuccess
import com.demo.mvvmcomposehilt.domain.mapper.CurrentMatchMapper
import com.demo.mvvmcomposehilt.domain.repository.CricketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMatchInfoUseCaseImpl @Inject constructor(
    private val repository: CricketRepository
) : GetMatchInfoUseCase {
    override suspend fun execute(matchId: String): Flow<Resource<CurrentMatchMapper>> = flow {
        repository.getMatchInfo(matchId).collect { response ->
            response.onSuccess { matchDetail ->
                emit(Resource.Success(matchDetail))
            }.onLoading {
                emit(Resource.Loading)
            }.onError {
                emit(Resource.Error(it))
            }
        }
    }
}