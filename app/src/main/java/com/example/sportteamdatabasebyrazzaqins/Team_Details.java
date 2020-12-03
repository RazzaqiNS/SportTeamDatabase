package com.example.sportteamdatabasebyrazzaqins;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Team_Details extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    Team_ModelRealm teamModel;


    Bundle extras;
    String teamnama;
    String country;
    String deskripsi;
    String badge;
    String id;


    TextView tvnamateam;
    ImageView ivbadge;
    TextView tvdesc;
    Button btnbookmark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team__details);

        extras = getIntent().getExtras();
        tvnamateam = (TextView)findViewById(R.id.tvnamateam);
        tvdesc = (TextView)findViewById(R.id.txtdeskripsi);
        ivbadge = (ImageView) findViewById(R.id.ivbadge);
        btnbookmark = (Button) findViewById(R.id.btnbookmark);

        if (extras != null) {
            teamnama = extras.getString("namateam");
            id = extras.getString("idTeam");
            country = extras.getString("negara");
            deskripsi = extras.getString("deskripsi");
            badge = extras.getString("badge");
            tvnamateam.setText(teamnama);
            tvdesc.setText(deskripsi);
            Glide.with(Team_Details.this)
                    .load(badge)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivbadge);
        }

        //Set up Realm
        Realm.init(Team_Details.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);


        btnbookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Team Dimasukkan ke Favorit",Toast.LENGTH_SHORT).show();
                teamModel = new Team_ModelRealm();
                teamModel.setTeamDescription(deskripsi);
                teamModel.setTeamName(teamnama);
                teamModel.setBadge(badge);
                teamModel.setCountry(country);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(teamModel);

            }
        });

    }


}