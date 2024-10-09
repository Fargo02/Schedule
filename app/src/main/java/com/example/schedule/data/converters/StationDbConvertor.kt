package com.example.schedule.data.converters

import com.example.schedule.domain.model.StationCode
import com.practicum.mymovies.data.db.entity.ScheduleEntity


class StationDbConvertor {

    fun map(station: StationCode): ScheduleEntity {
        return ScheduleEntity(
            yandexCode = station.yandexCode ?: "",
            esrCode = station.esrCode ?: "",
            direction = station.direction ?: "",
            stationType = station.stationType ?: "",
            title = station.title ?: "",
            transportType = station.transportType ?: ""
        )
    }

    fun map(station: ScheduleEntity): StationCode {
        return StationCode(
            yandexCode = station.yandexCode,
            esrCode = station.esrCode,
            direction = station.direction,
            stationType = station.stationType,
            title = station.title,
            transportType = station.transportType,
        )
    }
}