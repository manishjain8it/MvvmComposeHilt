package com.demo.mvvmcomposehilt.data.datasource

import com.demo.mvvmcomposehilt.BuildConfig
import com.demo.mvvmcomposehilt.data.common.Resource
import com.demo.mvvmcomposehilt.data.model.CurrentMatchesResponse
import com.demo.mvvmcomposehilt.data.model.MatchInfoResponse
import com.demo.mvvmcomposehilt.data.service.ApiService
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun getCurrentMatches(offset: Int): Resource<CurrentMatchesResponse> {
        return try {
            val response = apiService.getCurrentMatches(apiKey = BuildConfig.API_KEY, offset = offset)
            val body = response.body()
            if (response.isSuccessful && body != null && body.status == "success") {
                Resource.Success(body)
            } else {
                val errorMessage = body?.reason ?: "No data available"
                Resource.Error(Exception(errorMessage))
            }
        } catch (e: Throwable) {
            Resource.Error(Exception(e.message ?: "Unknown error"))
        }
    }

    override suspend fun getMatchInfo(offset: Int, matchId : String): Resource<MatchInfoResponse> {
        return try {
            val response = apiService.getMatchInfo(apiKey = BuildConfig.API_KEY, offset = offset, matchId = matchId)
            val body = response.body()
            if (response.isSuccessful && body != null && body.status == "success") {
                Resource.Success(body)
            } else {
                val errorMessage = body?.reason ?: "No data available"
                Resource.Error(Exception(errorMessage))
            }
        } catch (e: Throwable) {
            Resource.Error(Exception(e.message ?: "Unknown error"))
        }
    }
}