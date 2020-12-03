package com.example.sportteamdatabasebyrazzaqins;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Login_ControlClass extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        if (sharedPreferences.getString("username", "").isEmpty() ||
                sharedPreferences.getString("username", "").equalsIgnoreCase("")){
            //login dulu
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        }else{
            startActivity(new Intent(getApplicationContext(),Main_Menu.class));
            finish();
        }
    }
}
