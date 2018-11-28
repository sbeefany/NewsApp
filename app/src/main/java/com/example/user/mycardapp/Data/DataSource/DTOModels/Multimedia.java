package com.example.user.mycardapp.Data.DataSource.DTOModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Multimedia {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("copyright")
    @Expose
    private String copyright;

    public Multimedia (String url , String format , String copyright) {
        this.url = url;
        this.format = format;
        this.copyright = copyright;
    }

    public String getUrl () {
        return url;
    }

    public String getFormat () {
        return format;
    }

    public String getCopyright () {
        return copyright;
    }

}
