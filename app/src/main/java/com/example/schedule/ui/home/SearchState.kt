package com.example.schedule.ui.home

import com.example.schedule.domain.model.StationInfo

sealed interface SearchState {
    data object Loading : SearchState

    data class Content(
        val stationAndData: List<StationInfo>
    ) : SearchState

    data object Error : SearchState

    data object Empty : SearchState
}