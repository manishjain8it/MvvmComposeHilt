package com.demo.mvvmcomposehilt.domain.mapper

import com.demo.mvvmcomposehilt.data.model.CurrentMatchesModel
import com.demo.mvvmcomposehilt.data.model.ScoreModel

fun List<CurrentMatchesModel>.mapCurrentMatchMapper() = this.map { item ->
    CurrentMatchMapper(
        id = item.id,
        name = item.name,
        matchType = item.matchType,
        status = item.status,
        venue = item.venue,
        date = item.date,
        dateTimeGMT = item.dateTimeGMT,
        teams = item.teams,
        score = toScoreDomainModel(item.score),
        sessionId = item.sessionId,
        fantasyEnabled = item.fantasyEnabled,
        bbbEnabled = item.bbbEnabled,
        hasSquad = item.hasSquad,
        matchStarted = item.matchStarted,
        matchEnded = item.matchEnded
    )
}

private fun toScoreDomainModel(scores: List<ScoreModel>): List<Score> {
    return scores.map {
        Score(
            r = it.run,
            w = it.wicket,
            o = it.over,
            inning = it.inning
        )
    }
}

fun mapToMatchMapper(item : CurrentMatchesModel) : CurrentMatchMapper {
    return  CurrentMatchMapper(
        id = item.id,
        name = item.name,
        matchType = item.matchType,
        status = item.status,
        venue = item.venue,
        date = item.date,
        dateTimeGMT = item.dateTimeGMT,
        teams = item.teams,
        score = toScoreDomainModel(item.score),
        sessionId = item.sessionId,
        fantasyEnabled = item.fantasyEnabled,
        bbbEnabled = item.bbbEnabled,
        hasSquad = item.hasSquad,
        matchStarted = item.matchStarted,
        matchEnded = item.matchEnded
    )
}

