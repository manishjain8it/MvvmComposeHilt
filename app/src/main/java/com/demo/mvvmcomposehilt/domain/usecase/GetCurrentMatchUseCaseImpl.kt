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

class GetCurrentMatchUseCaseImpl @Inject constructor(
    private val repository: CricketRepository
) : GetCurrentMatchUseCase {
    override suspend fun execute(): Flow<Resource<List<CurrentMatchMapper>>> = flow {
        repository.getCurrentMatches().collect { response ->
            response.onSuccess { matchList ->
                emit(Resource.Success(matchList))
            }.onLoading {
                emit(Resource.Loading)
            }.onError {
                emit(Resource.Error(it))
            }
        }
    }
}