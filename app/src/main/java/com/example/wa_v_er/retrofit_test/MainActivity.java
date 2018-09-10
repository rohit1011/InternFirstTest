package com.example.wa_v_er.retrofit_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wa_v_er.retrofit_test.object.RetroPhoto;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView show = (TextView)findViewById(R.id.show);
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance(); //creating  database oject
        for (int i=0;i<2;i++) {
            RealmResults<RetroPhoto> results = realm.where(RetroPhoto.class).findAll();
            //fetching the data
            results.load();
            String output = "";
            for (RetroPhoto information : results) {
                output = information.getLanguage();
            }
            show.setText(output);
        }
    }
}
