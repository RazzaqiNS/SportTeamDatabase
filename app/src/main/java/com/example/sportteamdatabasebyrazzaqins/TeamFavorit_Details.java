package com.example.sportteamdatabasebyrazzaqins;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TeamFavorit_Details extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_favorit__details);

        extras = getIntent().getExtras();
        tvnamateam = (TextView)findViewById(R.id.tvnamateam);
        tvdesc = (TextView)findViewById(R.id.txtdeskripsi);
        ivbadge = (ImageView) findViewById(R.id.ivbadge);

        if (extras != null) {
            teamnama = extras.getString("namateam");
            id = extras.getString("id");
            country = extras.getString("negara");
            deskripsi = extras.getString("deskripsi");
            badge = extras.getString("badge");
            tvnamateam.setText(teamnama);
            tvdesc.setText(deskripsi);
            Glide.with(TeamFavorit_Details.this)
                    .load(badge)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivbadge);
        }
        //Set up Realm
        Realm.init(TeamFavorit_Details.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
    }


}