package com.tana1420.mybase.base.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.tana1420.mybase.base.presenter.BasePresenter;
import com.tana1420.mybase.utils.Utils;

public abstract class BaseDialog<T extends BasePresenter> extends DialogFragment {
    protected View vFragmentLayout;
    protected FragmentActivity activity;
    protected T presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        vFragmentLayout = setLayOutFragment(inflater, container);
        presenter = getPresenter();
        presenter.setActivity(activity);
        return vFragmentLayout;
    }

    //add view
    protected abstract View setLayOutFragment(LayoutInflater inflater, ViewGroup container);

    //get presenter
    protected abstract T getPresenter();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // hide systemBars
        Dialog dialog = getDialog();
        if (dialog != null) {
            Utils.hideSystemBars(dialog.getWindow());
        }
    }

}
