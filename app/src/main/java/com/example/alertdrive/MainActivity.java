package com.example.alertdrive;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.alertdrive.Fragment.FragmentBottom;


public class MainActivity extends AppCompatActivity {

    public  Button buttonright;
    public  Button buttonleft;
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
