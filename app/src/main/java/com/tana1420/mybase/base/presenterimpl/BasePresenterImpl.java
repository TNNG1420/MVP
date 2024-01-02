package com.tana1420.mybase.base.presenterimpl;

import android.content.Context;
import android.os.Bundle;

import com.tana1420.mybase.base.presenter.BasePresenter;
import com.tana1420.mybase.base.view.BaseView;

public class BasePresenterImpl<T extends BaseView> implements BasePresenter {

    protected  T mView;

    protected Context activity;
//
//    public BasePresenterImpl(T mView) {
//        this.mView = mView;
//    }
//
//    @Override
//    public void start(Bundle savedInstanceState) {
//        if (mView != null) {
//            mView.onInitView(savedInstanceState);
//        }
//    }
//
    public BasePresenterImpl(T mView) {
        this.mView = mView;
    }
    @Override
    public void setActivity(Context activity) {
        this.activity = activity;
    }
}
