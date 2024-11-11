package com.demo.mvvmcomposehilt.data.datasource

import com.demo.mvvmcomposehilt.data.common.Resource
import com.demo.mvvmcomposehilt.data.model.CurrentMatchesResponse
import com.demo.mvvmcomposehilt.data.model.MatchInfoResponse

interface RemoteDataSource {
    suspend fun getCurrentMatches(offset: Int): Resource<CurrentMatchesResponse>
    suspend fun getMatchInfo(offset: Int, matchId: String): Resource<MatchInfoResponse>
}