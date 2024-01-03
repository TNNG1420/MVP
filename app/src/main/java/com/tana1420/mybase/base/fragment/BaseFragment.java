package com.tana1420.mybase.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tana1420.mybase.MainActivity;
import com.tana1420.mybase.base.presenterimpl.BasePresenterImpl;
import com.tana1420.mybase.base.view.BaseView;

public abstract class BaseFragment<T extends BasePresenterImpl> extends Fragment implements BaseView {

    protected View vFragmentLayout;

    protected MainActivity activity;
    protected T presenter;
    private Bundle dataTransfer;

    protected abstract T getPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            dataTransfer = this.getArguments();
        }
//        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(threadPolicy);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        vFragmentLayout = setLayoutFragment(inflater, container);
        presenter = getPresenter();
        return vFragmentLayout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            initView();
            setActions();
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            initView();
            setActions();
        }
    }

    public abstract void initView();

    public abstract void setActions();

    public abstract View setLayoutFragment(LayoutInflater inflater, ViewGroup container);


    public Object getBundleDataTransferString(String key) {
        if (dataTransfer == null) {
            return null;
        }
        return dataTransfer.get(key);
    }

    public void showLoadingDialog() {
        if (activity != null) {
            activity.showLoading();
        }
    }

    public void hideLoadingDialog() {
        if (activity != null) {
            activity.hideLoading();
        }
    }

}
