package com.example.ivanpaulrutale.convergelevelup.view;

import android.os.Bundle;
import android.os.Parcelable;
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
    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);


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
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }


    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        // Save list state
        listState = linearLayoutManager.onSaveInstanceState();
        state.putParcelable(LIST_STATE_KEY, listState);
    }

    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        // Retrieve list state and list/item positions
        if(state != null)
            listState = state.getParcelable(LIST_STATE_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listState != null) {
            linearLayoutManager.onRestoreInstanceState(listState);
        }
    }
}
