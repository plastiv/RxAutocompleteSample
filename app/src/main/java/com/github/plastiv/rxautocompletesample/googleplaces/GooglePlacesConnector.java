package com.github.plastiv.rxautocompletesample.googleplaces;

import com.github.plastiv.rxautocompletesample.googleplaces.model.PlaceDetailsResult;
import com.github.plastiv.rxautocompletesample.googleplaces.model.PredictionResult;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface GooglePlacesConnector {

    final String PLACES_BASE_URL = "https://maps.googleapis.com/maps/api/place";

    @GET("/autocomplete/json?types=address")
    Observable<PredictionResult> autocomplete(@Query("input") String input, @Query("components") String components);

    @GET("/details/json")
    Observable<PlaceDetailsResult> details(@Query("placeid") String placeId);
}
