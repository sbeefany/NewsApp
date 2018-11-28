package com.example.user.mycardapp.Data.DataSource;

import com.example.user.mycardapp.Data.DataSource.DTOModels.DTONewsModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsApi {

    @GET("{category}.json")
    Observable<DTONewsModel> getNews (@Path("category") String category);

}
