package com.github.plastiv.rxautocompletesample.viewmodel;

import java.util.Collections;
import java.util.List;

import android.content.Context;

public abstract class BindableArrayAdapter<T> extends BindableAdapter<T> {

    private List<T> list;

    public BindableArrayAdapter(Context context) {
        this(context, null);
    }

    public BindableArrayAdapter(Context context, List<T> list) {
        super(context);
        if (list == null) {
            this.list = Collections.emptyList();
        } else {
            this.list = list;
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> newList) {
        if (list == null) {
            this.list = Collections.emptyList();
        } else {
            this.list = newList;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }
}
