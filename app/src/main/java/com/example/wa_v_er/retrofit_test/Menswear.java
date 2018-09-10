package com.example.wa_v_er.retrofit_test;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.wa_v_er.retrofit_test.adapter.CustomAdapter;
import com.example.wa_v_er.retrofit_test.network.GetDataService;
import com.example.wa_v_er.retrofit_test.network.Mens_Wear;
import com.example.wa_v_er.retrofit_test.network.RetrofitClientInstance;
import com.example.wa_v_er.retrofit_test.object.RetroPhoto;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menswear extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private Realm realm;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_womens_wear);
        progressDoalog = new ProgressDialog(Menswear.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        Realm.init(this);// Get a Realm instance for this thread
       realm = Realm.getDefaultInstance();

        /*Create handle for the RetrofitInstance interface*/
        Mens_Wear service = RetrofitClientInstance.getRetrofitInstance().create(Mens_Wear.class);
        Call<List<RetroPhoto>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
                insertdata(response.body());
                                           }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(Menswear.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
        readdata();

    }

    private void insertdata(List<RetroPhoto> list) {
        realm.beginTransaction();  //open the database
        //database operation
        realm.deleteAll();
         //this will create a information object which will be inserted in dtabase
        for (int i=0;i<list.size();i++)
        {
            RetroPhoto obj = realm.createObject(RetroPhoto.class,list.get(i).getLanguage());
            obj.setUrl(list.get(i).getUrl());
            obj.setPrice(list.get(i).getPrice());
            obj.setProducer(list.get(i).getProducer());
            Log.i("list","error"+list.get(i).getLanguage());
        } //inserted all data to database
        realm.commitTransaction(); //close the database
    }


    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<RetroPhoto> photoList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public void readdata(){

        RealmResults<RetroPhoto> results1 =realm.where(RetroPhoto.class).findAll();

        for (RetroPhoto i : results1) {
            ArrayList<RetroPhoto> show =new ArrayList<>();
            if(results1!=null){
                  show.addAll(realm.copyFromRealm(results1));
                recyclerView = findViewById(R.id.customRecyclerView);
                adapter = new CustomAdapter(this,show);
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }
            Log.d("results1", i.getProducer());
        }
    }


}