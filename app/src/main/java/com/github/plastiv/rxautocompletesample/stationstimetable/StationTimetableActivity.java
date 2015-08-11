package com.github.plastiv.rxautocompletesample.stationstimetable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.github.plastiv.rxautocompletesample.R;

public class StationTimetableActivity extends Activity {

    private static final String TAG = "AutocompleteActivity";

    private ListView addressListView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_timetable);
        injectDependencies();
    }

    private void injectDependencies() {

    }
}
