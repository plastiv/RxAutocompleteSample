package com.github.plastiv.rxautocompletesample.stationstimetable.network.model;

import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public class StationTimetableResponse {
    private StationTimetable timetable;

    public StationTimetable getTimetable() {
        return timetable;
    }
}
