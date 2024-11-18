package com.codinginflow.bigots;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/* loaded from: classes3.dex */
public class Helper {
    public static void getListViewSize(ListView myListView, boolean kontrol) {
        ListAdapter myListAdapter = myListView.getAdapter();
        if (myListAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int size = 0; size < myListAdapter.getCount(); size++) {
            View listItem = myListAdapter.getView(size, null, myListView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = myListView.getLayoutParams();
        if (kontrol) {
            params.height = (myListView.getDividerHeight() * (myListAdapter.getCount() - 90)) + totalHeight;
        } else {
            params.height = (myListView.getDividerHeight() * (myListAdapter.getCount() - 1)) + totalHeight;
            myListView.setLayoutParams(params);
        }
    }
}