package com.gp.namastecorona.api;

import com.gp.namastecorona.model.ConfirmModel;

import retrofit2.Call;
import retrofit2.http.GET;
/**
 * Created by gp
 */

public interface ApiInterface {

    @GET("confirmed")
    Call<ConfirmModel> getConfirmed();

    @GET("deaths")
    Call<ConfirmModel> getDeaths();

    @GET("recovered")
    Call<ConfirmModel> getRecovered();

}
