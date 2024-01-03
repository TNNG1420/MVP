package com.tana1420.mybase;

import static com.tana1420.mybase.utils.Constants.DURATION_TIME_CLICKABLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.tana1420.mybase.base.loading.LoadingDialog;
import com.tana1420.mybase.ui.demo.MFragment;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private LoadingDialog loadingDialog;

    private AtomicBoolean isLoadingShowing;

    // listen, detect network change
    private ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback networkCallback;

    // variable check double click
    private long lastTimeClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVariable();
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        connectivityManager = getSystemService(ConnectivityManager.class);
        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                // show dialog
            }
        };
        connectivityManager.registerDefaultNetworkCallback(networkCallback);
    }

    private void initVariable() {
        loadingDialog = new LoadingDialog();
        isLoadingShowing = new AtomicBoolean(false);
    }

    private void initView() {
        fragmentManager = getSupportFragmentManager();
        replaceFragmentAnimation(new MFragment());
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() <= 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public void showLoading() {
        if (!isLoadingShowing.getAndSet(true)) {
            loadingDialog.show(fragmentManager, null);
            loadingDialog.setCancelable(false);
        }
    }

    public void hideLoading() {
        if (isLoadingShowing.getAndSet(false)) {
            loadingDialog.dismiss();
        }
    }

    public boolean checkDoubleClick() {
        long timeNow = SystemClock.elapsedRealtime();
        if (timeNow - lastTimeClick >= DURATION_TIME_CLICKABLE) {
            Log.e("TAG", "checkDoubleClick: false "+ (timeNow - lastTimeClick));
            lastTimeClick = timeNow;
            return false;
        }

        Log.e("TAG", "checkDoubleClick: true "+ (timeNow - lastTimeClick));
            return true;
    }

    public void replaceFragmentAnimation(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.replace(R.id.panel, fragment);
        ft.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager.unregisterNetworkCallback(networkCallback);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}