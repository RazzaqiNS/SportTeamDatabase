package com.example.sportteamdatabasebyrazzaqins;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Team_DataList extends AppCompatActivity {

    TextView tvnodata;
    ProgressDialog dialog;
    RecyclerView recyclerView;
    DataAdapter adapter;
    ArrayList<Model> DataArrayList; //kit add kan ke adapter
    ImageView tambah_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team__data_list);
        getSupportActionBar().hide();
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        dialog = new ProgressDialog(Team_DataList.this);
        tvnodata = (TextView) findViewById(R.id.tvnodata);
        tvnodata.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        //addData();
        addDataOnline();
    }

    void addData() {
        //offline, isi data offline dulu
        DataArrayList = new ArrayList<>();
        Model data1 = new Model();
        data1.setTeamName("Nama Team");
        data1.setBadge("https://www.thesportsdb.com//images//media//team//badge//a1af2i1557005128.png");
        data1.setCountry("Negara");
        DataArrayList.add(data1);


        adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
            @Override
            public void onClick(int position) {

            }

            @Override
            public void test() {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Team_DataList.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    void addDataOnline(){
        //kasih loading
        dialog.setMessage("Sedang memproses data");
        dialog.show();
        // background process
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini
                        DataArrayList = new ArrayList<>();
                        Model modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("teams");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new Model();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setIdTeam(jsonObject.getInt("idTeam"));
                                modelku.setTeamName(jsonObject.getString("strTeam"));
                                modelku.setBadge(jsonObject.getString("strTeamBadge"));
                                modelku.setCountry(jsonObject.getString("strCountry"));
                                modelku.setTeamDescription(jsonObject.getString("strDescriptionEN"));
                                DataArrayList.add(modelku);
                            }
                            //untuk handle click
                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    Model team = DataArrayList.get(position);
                                    Intent intent = new Intent(getApplicationContext(), Team_Details.class);
                                    intent.putExtra("idTeam",team.idTeam);
                                    intent.putExtra("namateam",team.TeamName);
                                    intent.putExtra("negara",team.Country);
                                    intent.putExtra("deskripsi",team.TeamDescription);
                                    intent.putExtra("badge",team.Badge);
                                    startActivity(intent);
                                    Toast.makeText(Team_DataList.this, ""+position, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Team_DataList.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                    }
                });
    }
}