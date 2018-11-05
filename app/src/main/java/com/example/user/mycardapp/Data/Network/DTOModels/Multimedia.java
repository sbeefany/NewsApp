package com.example.user.mycardapp.Data.Network.DTOModels;

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

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getFormat () {
        return format;
    }

    public void setFormat (String format) {
        this.format = format;
    }

    public String getCopyright () {
        return copyright;
    }

    public void setCopyright (String copyright) {
        this.copyright = copyright;
    }

}
