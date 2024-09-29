package com.example.schedule.ui.home.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedule.domain.api.ScheduleInteractor
import com.example.schedule.domain.db.StationCodeInteractor
import com.example.schedule.domain.model.StationInfo
import com.example.schedule.ui.home.SearchState
import kotlinx.coroutines.launch


class HomeViewModel(
    private val valScheduleBetweenInteractor: ScheduleInteractor,
    private val stationCodeInteractor: StationCodeInteractor,
): ViewModel() {

    private val stateLiveData = MutableLiveData<SearchState>()
    fun observeState() : LiveData<SearchState> = stateLiveData

    fun requestToServer(fromCode: String, toCode: String, date: String, transportTypes: String) {

        renderState(SearchState.Loading)

        viewModelScope.launch {
            valScheduleBetweenInteractor
                .getScheduleByStationCodeAndDate(fromCode, toCode, date, transportTypes)
                .collect { pair ->
                    processResult(pair.first, pair.second)
                }
        }
    }

    fun getStationCode(stationName: String) {
        stationCodeInteractor.historyMovies()
    }

    private fun processResult(foundRoutes: List<StationInfo>?, errorMessage: String?) {
        val routes = mutableListOf<StationInfo>()

        if (foundRoutes != null) {
            routes.addAll(foundRoutes)
        }

        when {
            errorMessage != null -> {
                renderState(
                    SearchState.Error
                )
            }
            routes.isEmpty() -> {
                SearchState.Empty
            }
            else -> {
                renderState(
                    SearchState.Content(
                        stationAndData = routes,
                    )
                )
            }
        }
    }

    private fun renderState(state: SearchState) {
        stateLiveData.postValue(state)
    }
}