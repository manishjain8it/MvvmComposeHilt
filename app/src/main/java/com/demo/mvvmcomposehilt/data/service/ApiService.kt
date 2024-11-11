package com.demo.mvvmcomposehilt.data.service

import com.demo.mvvmcomposehilt.data.model.CurrentMatchesResponse
import com.demo.mvvmcomposehilt.data.model.MatchInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/currentMatches")
    suspend fun getCurrentMatches(@Query("apikey") apiKey: String, @Query("offset") offset: Int): Response<CurrentMatchesResponse>

    @GET("v1/match_info")
    suspend fun getMatchInfo(@Query("apikey") apiKey: String, @Query("offset") offset: Int, @Query("id") matchId: String): Response<MatchInfoResponse>
}