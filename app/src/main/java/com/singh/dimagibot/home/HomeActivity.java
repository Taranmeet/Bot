package com.singh.dimagibot.home;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;

import com.singh.dimagibot.R;
import com.singh.dimagibot.base.BaseActivity;
import com.singh.dimagibot.botService.DimagiBotService;
import com.singh.dimagibot.databinding.ActivityHomeBinding;


/**
 * Home activity showcasing the screen used to communicate with Dimagi Bot
 */
public class HomeActivity extends BaseActivity<HomeViewModel> implements IHomeView{

    private ActivityHomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        setViewModal(new HomeViewModel(this));

        mBinding.setViewModel(getViewModal());

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, DimagiBotService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to Dimagi Service and can now talk to the dimagi bot.
            DimagiBotService.DimagiBinder binder = (DimagiBotService.DimagiBinder) service;
            getViewModal().setDimagiBot(binder.getDimagiBot());
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            // dimagi bot is down.
        }
    };
}
