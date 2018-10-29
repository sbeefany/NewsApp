package com.example.user.mycardapp.Data.Network.DTOModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DTONewsModel {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public List<Result> getResults () {
        return results;
    }

    public void setResults (List<Result> results) {
        this.results = results;
    }

}
