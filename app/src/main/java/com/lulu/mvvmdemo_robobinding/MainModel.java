package com.lulu.mvvmdemo_robobinding;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.view.View;

import org.robobinding.presentationmodel.HasPresentationModelChangeSupport;
import org.robobinding.presentationmodel.PresentationModelChangeSupport;

import java.util.Random;

/**
 *
 * Created by LuXiaoYun on 2016/1/20.
 */
@org.robobinding.annotation.PresentationModel
public class MainModel implements HasPresentationModelChangeSupport {

    private PresentationModelChangeSupport changeSupport;

    private MainActivity mView;
    private Resources mResources;

    private String numberOfUsersLoggedIn = "...";
    private boolean isExistingUserChecked = true;
    private int emailBlockVisibility = View.GONE;
    private String loginOrCreateButtonText = "";

    public MainModel(MainActivity view, Resources resources) {
        changeSupport = new PresentationModelChangeSupport(this);
        mView = view;
        mResources = resources;
        loginOrCreateButtonText = mResources.getString(R.string.log_in);
    }

    @Override
    public PresentationModelChangeSupport getPresentationModelChangeSupport() {
        return changeSupport;
    }

    public String getNumberOfUsersLoggedIn() {
        return numberOfUsersLoggedIn;
    }

    public int getEmailBlockVisibility() {
        return  emailBlockVisibility;
    }

    public String getLoginOrCreateButtonText() {
        return  loginOrCreateButtonText;
    }

    public void returningUserRbOnClick() {
        emailBlockVisibility = View.GONE;
        loginOrCreateButtonText = mResources.getString(R.string.log_in);
        changeSupport.firePropertyChange("emailBlockVisibility");
        changeSupport.firePropertyChange("loginOrCreateButtonText");
        isExistingUserChecked = true;
    }

    public void newUserRbOnClick() {
        emailBlockVisibility = View.VISIBLE;
        loginOrCreateButtonText = mResources.getString(R.string.create_user);
        changeSupport.firePropertyChange("emailBlockVisibility");
        changeSupport.firePropertyChange("loginOrCreateButtonText");
        isExistingUserChecked = false;
    }

    public void loginOrCreateButtonOnClick() {
        if (isExistingUserChecked) {
            mView.showShortToast("Invalid username or password");
        } else {
            mView.showShortToast("Please enter a valid email address");
        }
    }

    public void loadAsync() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                numberOfUsersLoggedIn = "" + new Random().nextInt(1000);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                changeSupport.firePropertyChange("numberOfUsersLoggedIn");
            }
        }.execute((Void) null);
    }
}
