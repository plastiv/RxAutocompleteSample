package com.github.plastiv.rxautocompletesample.googleplaces;

import java.util.concurrent.TimeUnit;

import android.util.Log;

import com.github.plastiv.rxautocompletesample.googleplaces.model.Result;
import com.github.plastiv.rxautocompletesample.googleplaces.model.PlaceDetailsResult;
import com.github.plastiv.rxautocompletesample.googleplaces.model.PredictionResult;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class GooglePlacesErrorConnector implements GooglePlacesConnector {

    private final GooglePlacesConnector delegateConnector;

    public GooglePlacesErrorConnector(GooglePlacesConnector delegateConnector) {
        this.delegateConnector = delegateConnector;
    }

    @Override public Observable<PredictionResult> autocomplete(String input, String components) {
        return delegateConnector.autocomplete(input, components)
                                .flatMap(new ApiErrorCheck<PredictionResult>())
                                .timeout(20, TimeUnit.SECONDS)
                                .doOnError(new LogErrorAction(
                                        "Failed to load autocomplete with input: %s, components: %s",
                                        input, components))
                                .subscribeOn(Schedulers.io());
    }

    @Override public Observable<PlaceDetailsResult> details(String placeId) {
        return delegateConnector.details(placeId)
                                .flatMap(new ApiErrorCheck<PlaceDetailsResult>())
                                .timeout(20, TimeUnit.SECONDS)
                                .doOnError(new LogErrorAction("Failed to load details with placeId: %s", placeId))
                                .subscribeOn(Schedulers.io());
    }

    private static class ApiErrorCheck<T extends Result> implements Func1<T, Observable<T>> {
        @Override public Observable<T> call(T result) {
            if (result.isSuccessful()) {
                return Observable.just(result);
            } else {
                return Observable.error(new GooglePlacesApiError(result.getStatus()));
            }
        }
    }

    private static class LogErrorAction implements Action1<Throwable> {

        private final String errorMessage;

        public LogErrorAction(String message, Object... args) {
            this.errorMessage = args.length == 0 ? message : String.format(message, args);
        }

        @Override public void call(Throwable throwable) {
            Log.e("RxLog", errorMessage, throwable);
        }
    }
}
