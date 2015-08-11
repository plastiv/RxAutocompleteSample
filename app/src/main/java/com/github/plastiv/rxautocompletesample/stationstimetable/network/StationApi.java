package com.github.plastiv.rxautocompletesample.stationstimetable.network;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface StationApi {
    @GET("/mobile/v1/network/station/{stationId}/timetable")
    Observable<StationTimetable> loadStationTimetable(@Path("stationId") String stationId);
}
