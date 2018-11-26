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

    public Result (String title , String _abstract , String publishedDate , List<Multimedia> multimedia , String url) {
        this.title = title;
        this._abstract = _abstract;
        this.publishedDate = publishedDate;
        this.multimedia = multimedia;
        this.url = url;
    }

    public String getTitle () {
        return title;
    }

    public String get_abstract () {
        return _abstract;
    }

    public String getPublishedDate () {
        return publishedDate;
    }

    public List<Multimedia> getMultimedia () {
        return multimedia;
    }

    public String getUrl () {
        return url;
    }

}
