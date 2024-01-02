package com.tana1420.mybase.ui.demo;

import com.tana1420.mybase.base.presenter.BasePresenter;
import com.tana1420.mybase.base.presenterimpl.BasePresenterImpl;

public class MPresenterImpl extends BasePresenterImpl<MView> implements MPresenter {

    public MPresenterImpl(MView mView) {
        super(mView);
    }

    @Override
    public void getMPresenter() {
        String s = "Hello";
        mView.getMView(s);
    }
}
