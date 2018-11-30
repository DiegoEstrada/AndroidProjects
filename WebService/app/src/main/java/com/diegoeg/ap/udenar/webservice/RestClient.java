package com.diegoeg.ap.udenar.webservice;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestClient {
    @GET("pokemon")
    Call<Result> getData();
}
