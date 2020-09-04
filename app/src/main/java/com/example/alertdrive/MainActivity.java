package com.example.alertdrive;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.alertdrive.Fragment.FragmentBottom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import com.example.alertdrive.Fragment.FragmentBottom;

public class MainActivity extends AppCompatActivity {

    public static Button buttonright;
    public static Button buttonleft;
    public static int page = 0;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

         buttonright= (Button) findViewById(R.id.right);


        buttonleft= (Button) findViewById(R.id.left);

        buttonright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page+=5;
                FragmentBottom currentFragment = (FragmentBottom) getFragmentManager().findFragmentByTag("frag_bottom");
                currentFragment.update(page);


            }
        });
        buttonleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page-=5;
                if(page <0){
                    page = 0;
                }

                FragmentBottom currentFragment = (FragmentBottom) getFragmentManager().findFragmentByTag("frag_bottom");
                currentFragment.update(page);
            }
        });

    }


}
