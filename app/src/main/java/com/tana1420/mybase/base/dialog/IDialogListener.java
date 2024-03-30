package com.tana1420.mybase.base.dialog;

public interface IDialogListener {
    void confirmListener(int id);

    void cancelListener();

    void confirmRetryListener();
}
