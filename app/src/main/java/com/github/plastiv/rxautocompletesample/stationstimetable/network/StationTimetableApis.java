package com.github.plastiv.rxautocompletesample.stationstimetable.network;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class StationTimetableApis {

    private static final String BASE_URL = "https://api.mobile.staging.mfb.io";

    private StationTimetableApis() {
        throw new AssertionError("No instances.");
    }

    public static StationTimetableApi getInstance() {
        return newRetrofitInstance();
    }

    private static StationTimetableApi newRetrofitInstance() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setRequestInterceptor(createRequestInterceptor())
                .setEndpoint(BASE_URL)
                .build();

        return restAdapter.create(StationTimetableApi.class);
    }

    private static RequestInterceptor createRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                // same key required for every api call
                request.addHeader("X-Api-Authentication", "47NqJvHSC;uFYD96a4B4+5yTZ9By");
            }
        };
    }
}
