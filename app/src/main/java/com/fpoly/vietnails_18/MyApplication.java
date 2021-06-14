package com.fpoly.vietnails_18;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication mSelf;

    public static MyApplication self() {
        return mSelf;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSelf = this;
    }


}
