package com.example.ivanpaulrutale.convergelevelup.service;

import com.example.ivanpaulrutale.convergelevelup.model.MainJsonMapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDeveloperDataService {
    @GET("search/users?q=type:User+location:Nairobi+language:JAVA")
    Call<MainJsonMapper> getDevelopers();
}
