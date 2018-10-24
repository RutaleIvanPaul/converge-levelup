package com.example.ivanpaulrutale.convergelevelup.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ivanpaulrutale.convergelevelup.ListAdapter;
import com.example.ivanpaulrutale.convergelevelup.R;
import com.example.ivanpaulrutale.convergelevelup.model.DeveloperDataMapper;
import com.example.ivanpaulrutale.convergelevelup.presenter.DeveloperPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DeveloperView{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DeveloperDataMapper> listitems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DeveloperPresenter developerPresenter = new DeveloperPresenter(this);
        developerPresenter.getDeveloperNames();

    }


    @Override
    public void DevelopersReady(List<DeveloperDataMapper> developers) {
        adapter = new ListAdapter(developers,this);
        setRecyclerView();

        for (DeveloperDataMapper developerDataMapper: developers){
            Log.i("RETROFIT","Developer:"+developerDataMapper.getLogin());
        }
    }

    public void setRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
