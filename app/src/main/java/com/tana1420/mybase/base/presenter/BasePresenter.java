package com.tana1420.mybase.base.presenter;

import android.content.Context;

public interface BasePresenter {





//    /*
//     *
//     * Use to handle onCreate of activity or onViewCreated
//     * @Param saveInstanceState: have data when use onSaveInstanceState
//     *
//     */
//    void start(Bundle savedInstanceState);

//    /*
//     *
//     * Use to get activity view attach
//     * @Param1 activity: get current activity
//     *
//     */
    void setActivity(Context activity);
}
