package com.example.sportteamdatabasebyrazzaqins;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TeamFavourite_DataList extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    TextView tvnodata;
    RecyclerView recyclerView;
    DataAdapter_Favorit adapter;
    List<Team_ModelRealm> DataArrayList; //kit add kan ke adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamfavourite_datalist);
        getSupportActionBar().hide();


        tvnodata = (TextView) findViewById(R.id.tvnodata);
        recyclerView = (RecyclerView) findViewById( R.id.rvdata );
        DataArrayList = new ArrayList<>();
        // Setup Realm
        tvnodata = (TextView) findViewById( R.id.tvnodata );
        Realm.init(TeamFavourite_DataList.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance( configuration );
        realmHelper = new RealmHelper( realm );
        DataArrayList = realmHelper.getAllTeam();
        if (DataArrayList.size() == 0) {
            tvnodata.setVisibility( View.VISIBLE );
            recyclerView.setVisibility( View.GONE );

        } else {
            tvnodata.setVisibility( View.GONE );
            recyclerView.setVisibility( View.VISIBLE );
            adapter = new DataAdapter_Favorit(DataArrayList, new DataAdapter_Favorit.Callback() {
                @Override
                public void onClick(int position) {
                    Intent move = new Intent(getApplicationContext(), TeamFavorit_Details.class);
                    move.putExtra("namateam",DataArrayList.get(position).getTeamName());
                    move.putExtra("badge",DataArrayList.get(position).getBadge());
                    move.putExtra("negara",DataArrayList.get(position).getCountry());
                    move.putExtra("deskripsi",DataArrayList.get(position).getTeamDescription());
                    // di putextra yang lain
                    startActivity(move);
                }

                @Override
                public void test() {

                }
            });
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TeamFavourite_DataList.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}
