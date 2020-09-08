package com.example.alertdrive.Fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.app.Fragment

import com.example.alertdrive.MainActivity
import com.example.alertdrive.R

class FragmentTop : Fragment() {

    //internal var activity = getActivity() as MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val retView = inflater.inflate(R.layout.fragment_top, container)

        val search_bar = retView.findViewById<View>(R.id.Search_Bar) as EditText

        val androidButton = retView.findViewById<View>(R.id.All_List) as Button
        androidButton.setOnClickListener {
           // activity.page = 0
            val currentFragment = fragmentManager.findFragmentByTag("frag_bottom") as FragmentBottom
            currentFragment.filterall()
        }
        val iosButton = retView.findViewById<View>(R.id.Complete_List) as Button
        iosButton.setOnClickListener {
           // activity.page = 0
            val currentFragment = fragmentManager.findFragmentByTag("frag_bottom") as FragmentBottom
            currentFragment.filtercompleted()
        }
        val windowsButton = retView.findViewById<View>(R.id.Incomplete_List) as Button
        windowsButton.setOnClickListener {
           // activity.page = 0
            val currentFragment = fragmentManager.findFragmentByTag("frag_bottom") as FragmentBottom
            currentFragment.filterIncompleted()
        }

        search_bar.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
              //  activity.page = 0
                val text = search_bar.text.toString()
                val currentFragment = fragmentManager.findFragmentByTag("frag_bottom") as FragmentBottom
                currentFragment.filtersearch(text)
                return@OnKeyListener true
            }
            false
        })


        return retView
    }
}