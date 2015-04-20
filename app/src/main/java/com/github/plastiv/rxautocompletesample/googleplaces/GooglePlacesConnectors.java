package com.github.plastiv.rxautocompletesample.googleplaces;

import java.util.Locale;

import com.github.plastiv.rxautocompletesample.BuildConfig;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class GooglePlacesConnectors {

    private GooglePlacesConnectors() {
        throw new AssertionError("No instances.");
    }

    public static GooglePlacesConnector getInstance() {
        return new GooglePlacesErrorConnector(newRetrofitInstance());
    }

    private static GooglePlacesConnector newRetrofitInstance() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setRequestInterceptor(createRequestInterceptor())
                .setEndpoint(GooglePlacesConnector.PLACES_BASE_URL)
                .build();

        return restAdapter.create(GooglePlacesConnector.class);
    }

    private static RequestInterceptor createRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                // same key required for every api call
                request.addQueryParam("key", BuildConfig.GOOGLE_PLACES_API);
                request.addQueryParam("language", "de");
            }
        };
    }
}
