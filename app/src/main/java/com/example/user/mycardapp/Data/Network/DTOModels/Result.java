package com.example.user.mycardapp.Data.Network.DTOModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("multimedia")
    @Expose
    private List<Multimedia> multimedia = null;
    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String get_abstract () {
        return _abstract;
    }

    public void set_abstract (String _abstract) {
        this._abstract = _abstract;
    }

    public String getPublishedDate () {
        return publishedDate;
    }

    public void setPublishedDate (String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public List<Multimedia> getMultimedia () {
        return multimedia;
    }

    public void setMultimedia (List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }
}
