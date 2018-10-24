package com.example.ivanpaulrutale.convergelevelup.presenter;

import android.util.Log;

import com.example.ivanpaulrutale.convergelevelup.model.DeveloperDataMapper;
import com.example.ivanpaulrutale.convergelevelup.model.MainJsonMapper;
import com.example.ivanpaulrutale.convergelevelup.service.GetDeveloperDataService;
import com.example.ivanpaulrutale.convergelevelup.service.RetrofitInstance;
import com.example.ivanpaulrutale.convergelevelup.view.DeveloperView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeveloperPresenter {
    private DeveloperView developerView;
    private RetrofitInstance retrofitInstance;

    public DeveloperPresenter(DeveloperView view){
        this.developerView = view;

        if(this.retrofitInstance == null){
            this.retrofitInstance = new RetrofitInstance() ;
        }

    }

    public void getDeveloperNames() {
        GetDeveloperDataService getDeveloperDataService = RetrofitInstance.getService();
        Call<MainJsonMapper> call = getDeveloperDataService.getDevelopers();

        call.enqueue(new Callback<MainJsonMapper>() {
            @Override
            public void onResponse(Call<MainJsonMapper> call, Response<MainJsonMapper> response) {
                MainJsonMapper mainJsonMapper = response.body();

                if(mainJsonMapper != null && mainJsonMapper.getItems() != null){
                    List<DeveloperDataMapper>listitems = mainJsonMapper.getItems();
                    developerView.DevelopersReady(listitems);

                }
            }

            @Override
            public void onFailure(Call<MainJsonMapper> call, Throwable t) {
                try {
                    throw new InterruptedException("Something went wrong!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
