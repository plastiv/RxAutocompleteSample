package com.github.plastiv.rxautocompletesample.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.plastiv.rxautocompletesample.R;
import com.github.plastiv.rxautocompletesample.utils.ViewHolder;

public class AddressAdapter extends BindableArrayAdapter<AddressListItem> {

    public AddressAdapter(Context context) {
        super(context);
    }

    @Override public View newView(LayoutInflater inflater, int position, ViewGroup container) {
        return inflater.inflate(R.layout.address_list_item, container, false);
    }

    @Override public void bindView(AddressListItem item, int position, View view) {
        TextView firstLineView = ViewHolder.get(view, R.id.firstLineView);
        firstLineView.setText(item.getFirstLine());

        TextView secondLineView = ViewHolder.get(view, R.id.secondLineView);
        secondLineView.setText(item.getSecondLine());
    }
}
