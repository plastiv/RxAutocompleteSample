package com.github.plastiv.rxautocompletesample.stationstimetable.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.plastiv.rxautocompletesample.R;
import com.github.plastiv.rxautocompletesample.stationstimetable.network.model.Arrival;
import com.github.plastiv.rxautocompletesample.utils.ViewHolder;
import com.github.plastiv.rxautocompletesample.view.model.BindableArrayAdapter;

public class ArrivalsAdapter extends BindableArrayAdapter<Arrival> {

    public ArrivalsAdapter(Context context) {
        super(context);
    }

    public ArrivalsAdapter(Context context, List<Arrival> list) {
        super(context, list);
    }

    @Override
    public View newView(LayoutInflater inflater, int position, ViewGroup container) {
        return inflater.inflate(R.layout.list_item_arrival, container, false);
    }

    @Override
    public void bindView(Arrival item, int position, View view) {
        TextView firstLineView = ViewHolder.get(view, R.id.firstLineView);
        firstLineView.setText(item.getThrough_the_stations());
        TextView secondLineView = ViewHolder.get(view, R.id.secondLineView);
        secondLineView.setText(item.getDatetime().toFormattedString());
    }
}
