package com.singh.dimagibot.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Base class for activities
 * @param <T> The View modal type associated with activity/view.
 */
public class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {

    private T mViewModal;

    public T getViewModal(){
        return mViewModal;
    }

    public void setViewModal(T iViewModal){
        mViewModal = iViewModal;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // removing cyclic dependencies to avoid garbage collection issues
        mViewModal.onDestroy();
        mViewModal = null;
    }
}
