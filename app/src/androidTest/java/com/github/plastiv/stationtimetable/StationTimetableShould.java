package com.github.plastiv.stationtimetable;

import android.support.test.runner.AndroidJUnit4;

import com.github.plastiv.rxautocompletesample.stationstimetable.network.model.StationTimetableResponse;
import com.github.plastiv.rxautocompletesample.stationstimetable.network.StationTimetableApi;
import com.github.plastiv.rxautocompletesample.stationstimetable.network.StationTimetableApis;

import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Observable;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class StationTimetableShould {

    private StationTimetableApi stationTimetableApi;

    @Test
    public void download_response() throws Exception {
        stationTimetableApi = StationTimetableApis.getInstance();
        Observable<StationTimetableResponse> stationTimetableObservable = stationTimetableApi.loadStationTimetable("10");
        StationTimetableResponse first = stationTimetableObservable.toBlocking().first();
        assertTrue(first != null);
    }
}
