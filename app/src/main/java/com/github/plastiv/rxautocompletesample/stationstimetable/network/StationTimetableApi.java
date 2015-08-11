package com.github.plastiv.rxautocompletesample.stationstimetable.network;

import com.github.plastiv.rxautocompletesample.stationstimetable.network.model.StationTimetableResponse;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface StationTimetableApi {
    @GET("/mobile/v1/network/station/{stationId}/timetable")
    Observable<StationTimetableResponse> loadStationTimetable(@Path("stationId") String stationId);
}
