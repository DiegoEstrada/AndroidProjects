package com.diegoeg.ap.udenar.webservice;

import android.app.Activity;
import android.support.v4.app.ListFragment;

public class HeadlinesFragment extends ListFragment {
    OnHeadlineSelectedListener mCallback;

    public void setOnHeadlineSelectedListener(Activity activity) {
        mCallback = (OnHeadlineSelectedListener) activity;
    }

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }



    // ...
}