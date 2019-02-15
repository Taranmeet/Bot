package com.singh.dimagibot.login;

import android.content.Intent;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.widget.Toast;

import com.singh.dimagibot.base.BaseViewModel;
import com.singh.dimagibot.data.DataSource;
import com.singh.dimagibot.home.HomeActivity;

/**
 * Login View Modal encapsulates the login logic.
 */
public class LoginViewModal extends BaseViewModel<ILoginView> {

    public ObservableField<String> mUserName = new ObservableField<>();


    public LoginViewModal(ILoginView iView) {
        super(iView);
    }

    /**
     * Method called when set name button on login screen is clicked.
     */
    public void setNameClicked() {
        // validate data
        if (!TextUtils.isEmpty(mUserName.get())) {
            if (!DataSource.mLocation.containsKey(mUserName.get())) {

                // save data
                DataSource.mLocation.put(mUserName.get(), "Noida");
                DataSource.mCurrentUser = mUserName.get();

                // move to home screen.
                Intent intent = new Intent(getView().getContext(), HomeActivity.class);
                getView().getContext().startActivity(intent);

                // clear screen
                mUserName.set("");
            } else {
                Toast.makeText(getView().getContext(), "User name already taken.", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getView().getContext(), "Name cannot be empty.", Toast.LENGTH_SHORT).show();
        }
    }
}
