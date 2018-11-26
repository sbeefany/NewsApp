package com.example.user.mycardapp.Data.Network.DTOModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.annotations.Nullable;

public class DTONewsModel {

    @SerializedName("results")
    @Expose
    @Nullable
    private List<Result> results = null;

    public DTONewsModel (List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults () {
        return results;
    }

}
