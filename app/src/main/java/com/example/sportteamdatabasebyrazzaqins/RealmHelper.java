package com.example.sportteamdatabasebyrazzaqins;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class RealmHelper {

    Realm realm;

    public  RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final Team_ModelRealm teamRealm){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(Team_ModelRealm.class).max("idTeam");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    teamRealm.setIdTeam(nextId);
                    Team_ModelRealm model = realm.copyToRealm(teamRealm);
                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<Team_ModelRealm> getAllTeam(){
        RealmResults<Team_ModelRealm> results = realm.where(Team_ModelRealm.class).findAll();
        return results;
    }

    public void delete(Integer id){
        final RealmResults<Team_ModelRealm> model = realm.where(Team_ModelRealm.class).equalTo("idTeam", id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

}
