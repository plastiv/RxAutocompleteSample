package com.github.plastiv.rxautocompletesample.stationstimetable.view;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.github.plastiv.rxautocompletesample.R;
import com.github.plastiv.rxautocompletesample.stationstimetable.network.StationTimetableApi;
import com.github.plastiv.rxautocompletesample.stationstimetable.network.StationTimetableApis;
import com.github.plastiv.rxautocompletesample.stationstimetable.network.model.Arrival;
import com.github.plastiv.rxautocompletesample.stationstimetable.network.model.StationTimetableResponse;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;

public class StationTimetableActivity extends Activity {

    private static final String TAG = "AutocompleteActivity";

    private ListView arrivalsListView;
    private StationTimetableApi timetableApi;
    private ArrivalsAdapter arrivalsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_timetable);
        arrivalsListView = (ListView) findViewById(R.id.stationListView);
        arrivalsAdapter = new ArrivalsAdapter(this);
        arrivalsListView.setAdapter(arrivalsAdapter);
        injectDependencies();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timetableApi.loadStationTimetable("10")
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Func1<StationTimetableResponse, Observable<Arrival>>() {
                        @Override
                        public Observable<Arrival> call(StationTimetableResponse stationTimetableResponse) {
                            return Observable.from(stationTimetableResponse.getTimetable().getArrivals());
                        }
                    })
                    .toSortedList(new Func2<Arrival, Arrival, Integer>() {
                        @Override
                        public Integer call(Arrival arrival, Arrival arrival2) {
                            long timestamp1 = arrival2.getDatetime().getTimestamp();
                            long timestamp2 = arrival.getDatetime().getTimestamp();
                            return (int) (timestamp1 - timestamp2);
                        }
                    })
                    .subscribe(new Observer<List<Arrival>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable throwable) {
                            Toast.makeText(StationTimetableActivity.this, throwable.getMessage(), Toast.LENGTH_LONG)
                                 .show();
                        }

                        @Override
                        public void onNext(List<Arrival> arrivals) {
                            arrivalsAdapter.setList(arrivals);
                        }
                    });
    }

    private void injectDependencies() {
        timetableApi = StationTimetableApis.getInstance();
    }
}
