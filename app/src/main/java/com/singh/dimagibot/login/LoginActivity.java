package com.singh.dimagibot.login;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.singh.dimagibot.R;
import com.singh.dimagibot.base.BaseActivity;
import com.singh.dimagibot.databinding.ActivityLoginBinding;

/**
 * The Login activity emulating the login screen for users.
 */
public class LoginActivity extends BaseActivity<LoginViewModal> implements ILoginView {

    ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        setViewModal(new LoginViewModal(this));

        mBinding.setViewModel(getViewModal());

    }

    @Override
    public Context getContext() {
        return this;
    }
}
