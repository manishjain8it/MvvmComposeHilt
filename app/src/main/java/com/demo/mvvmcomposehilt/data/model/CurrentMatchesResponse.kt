package com.demo.mvvmcomposehilt.data.model

import com.google.gson.annotations.SerializedName

data class CurrentMatchesResponse(
    val apikey: String,
    val data : List<CurrentMatchesModel>,
    val status: String,
    val reason: String = ""
)

data class CurrentMatchesModel(
    val id: String,
    val name: String,
    val matchType: String,
    val status: String,
    val venue: String,
    val date: String,
    val dateTimeGMT: String,
    val teams: List<String>,
    val score: List<ScoreModel>,
    @SerializedName("series_id")
    val sessionId: String,
    val fantasyEnabled: Boolean,
    val bbbEnabled: Boolean,
    val hasSquad: Boolean,
    val matchStarted: Boolean,
    val matchEnded: Boolean
)

data class ScoreModel(
    val inning: String,
    @SerializedName("o")
    val over: Double,
    @SerializedName("r")
    val run: Int,
    @SerializedName("w")
    val wicket: Int
)
