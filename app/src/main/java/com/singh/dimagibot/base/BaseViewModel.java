package com.singh.dimagibot.base;

/**
 * Base class for view modals.
 * @param <T> Type of view associated with view modal
 */
public class BaseViewModel<T extends IBaseView> {
    private T mView;

    public T getView() {
        return mView;
    }

    public BaseViewModel(T iView){
        mView = iView;
    }

    // clean dependencies to help garbage collection.
    public void onDestroy(){
        mView = null;
    }
}
