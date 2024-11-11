package com.demo.mvvmcomposehilt.domain.mapper

data class CurrentMatchMapper(
    val id: String,
    val name: String,
    val matchType: String,
    val status: String,
    val venue: String,
    val date: String,
    val dateTimeGMT: String,
    val teams: List<String>,
    val score: List<Score>,
    val sessionId: String,
    val fantasyEnabled: Boolean,
    val bbbEnabled: Boolean,
    val hasSquad: Boolean,
    val matchStarted: Boolean,
    val matchEnded: Boolean
)

data class Score(
    val r: Int,
    val w: Int,
    val o: Double,
    val inning: String
)
