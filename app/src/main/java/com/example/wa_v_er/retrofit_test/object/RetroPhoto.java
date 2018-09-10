package com.example.wa_v_er.retrofit_test.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RetroPhoto extends RealmObject {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("language")
    @Expose
    @PrimaryKey
    private String language;
    @SerializedName("producer")
    @Expose
    private String producer;
    @SerializedName("price")
    @Expose
    private String price;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLanguage() {
        return language;
    }

        public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
