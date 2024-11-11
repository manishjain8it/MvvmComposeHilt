package com.demo.mvvmcomposehilt.data.model

data class MatchInfoResponse(
    val apikey: String,
    val data : CurrentMatchesModel,
    val status: String,
    val reason: String = ""
)
