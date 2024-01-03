package com.tana1420.mybase.ui.demo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tana1420.mybase.base.fragment.BaseFragment;
import com.tana1420.mybase.databinding.FragmentMBinding;

public class MFragment extends BaseFragment<MPresenterImpl> implements MView {

    // viewDataBinding - co the truy cap truc tiep den cac bien(id) cua layout
    // (phai khai bao buildFeatures trong build.gradle app)
    private FragmentMBinding binding;

    @Override
    protected MPresenterImpl getPresenter() {
        return new MPresenterImpl(this);
    }

    @Override
    public View setLayoutFragment(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentMBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void initView() {
        if (binding != null) {
            // khoi tao view trong ham nay
            binding.btn.setText("Show Hello");
            Toast.makeText(activity, "success: " + binding, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setActions() {
        // khoi tao cac event trong ham nay

        binding.btn.setOnClickListener(view -> {
            if (activity.checkDoubleClick()) {
                return;
            }
            presenter.getMPresenter();
            Log.e("TAG", "setActions: ");
        });
    }

    @Override
    public void getMView(String s) {
        Toast.makeText(activity, s, Toast.LENGTH_LONG).show();
    }

}