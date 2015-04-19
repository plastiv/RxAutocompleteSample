package com.github.plastiv.rxautocompletesample.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * ViewHolder patter implementation for savind findViewById operation at ListAdapter. See full description here:
 * http://www.piwai.info/android-adapter-good-practices/
 */
public final class ViewHolder {

    private ViewHolder() {
        throw new AssertionError("No incstances.");
    }

    // I added a generic return type to reduce the casting noise in client code
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {

        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
