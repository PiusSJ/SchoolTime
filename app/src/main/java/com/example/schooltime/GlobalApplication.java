package com.example.schooltime;

import android.app.Application;
import android.content.Context;

public class GlobalApplication extends Application{

    private static GlobalApplication application;

    public static Context getGlobalContext() {
        return GlobalApplication.application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
