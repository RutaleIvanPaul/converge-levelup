package com.example.ivanpaulrutale.convergelevelup.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ivanpaulrutale.convergelevelup.adapter.GithubAdapter;
import com.example.ivanpaulrutale.convergelevelup.R;
import com.example.ivanpaulrutale.convergelevelup.model.DeveloperDataMapper;
import com.example.ivanpaulrutale.convergelevelup.presenter.DeveloperPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DeveloperView, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<DeveloperDataMapper> listitems;
    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;
    DeveloperPresenter developerPresenter;
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
    ProgressDialog progressDialog;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading Data");
        progressDialog.setMessage("Processing, please wait...");
        progressDialog.show();

        swipeRefreshLayout = findViewById(R.id.swiper);
        swipeRefreshLayout.setOnRefreshListener(this);

        developerPresenter = new DeveloperPresenter(this);
        developerPresenter.getDeveloperNames();

    }


    @Override
    public void DevelopersReady(List<DeveloperDataMapper> developers) {
        adapter = new GithubAdapter(developers,this);
        setRecyclerView();
        progressDialog.dismiss();

    }

    public void setRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

    }


    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        // Save list state
        listState = gridLayoutManager.onSaveInstanceState();
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
            gridLayoutManager.onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onRefresh() {
        developerPresenter.getDeveloperNames();
        swipeRefreshLayout.setRefreshing(false);
    }

}
