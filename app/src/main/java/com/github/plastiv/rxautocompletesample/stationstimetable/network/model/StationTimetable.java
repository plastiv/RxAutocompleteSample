package com.github.plastiv.rxautocompletesample.stationstimetable.network.model;

import java.util.Collections;
import java.util.List;

public class StationTimetable {
    List<Arrival> arrivals;
    List<Depature> depatures;

    public List<Arrival> getArrivals() {
        if (arrivals == null) {
            return Collections.emptyList();
        }
        return arrivals;
    }

    public List<Depature> getDepatures() {
        // FIXME empli list if null
        return depatures;
    }
}
