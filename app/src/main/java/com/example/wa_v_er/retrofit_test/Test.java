package com.example.wa_v_er.retrofit_test;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Test extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

// Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}
