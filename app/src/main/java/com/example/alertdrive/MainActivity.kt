package com.example.alertdrive

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.alertdrive.Fragment.FragmentBottom


class MainActivity : AppCompatActivity() {



    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        var buttonright = findViewById<View>(R.id.right) as Button
        var buttonleft = findViewById<View>(R.id.left) as Button

        buttonright.setOnClickListener {
            page += 5
            val currentFragment = fragmentManager.findFragmentByTag("frag_bottom") as FragmentBottom
            currentFragment.update(page)
        }
        buttonleft.setOnClickListener {
            page -= 5
            if (page < 0) {
                page = 0
            }
            val currentFragment = fragmentManager.findFragmentByTag("frag_bottom") as FragmentBottom
            currentFragment.update(page)
        }
    }

    companion object {
        var page = 0
    }
}
