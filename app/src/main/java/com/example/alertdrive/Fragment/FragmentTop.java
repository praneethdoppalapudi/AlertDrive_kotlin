package com.example.alertdrive.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import android.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.alertdrive.R;

public class FragmentTop extends Fragment {

    EditText search_bar;

    // This method will be invoked when the Fragment view object is created.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.fragment_top, container);

        search_bar = (EditText)retView.findViewById(R.id.Search_Bar);

        if(retView!=null)
        {
            // Click this button will show the text in right fragment.
            Button androidButton = (Button)retView.findViewById(R.id.All_List);
            androidButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentBottom currentFragment = (FragmentBottom) getFragmentManager().findFragmentByTag("frag_bottom");
                    currentFragment.filterall();
                }
            });


            // Click this button will show a Toast popup.
            Button iosButton = (Button)retView.findViewById(R.id.Complete_List);
            iosButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentBottom currentFragment = (FragmentBottom) getFragmentManager().findFragmentByTag("frag_bottom");
                    currentFragment.filtercompleted();
                }
            });


            // Click this button will show an alert dialog.
            Button windowsButton = (Button)retView.findViewById(R.id.Incomplete_List);
            windowsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentBottom currentFragment = (FragmentBottom) getFragmentManager().findFragmentByTag("frag_bottom");
                    currentFragment.filterIncompleted();
                }
            });

            search_bar.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    // If the event is a key-down event on the "enter" button
                    if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        String text = search_bar.getText().toString();
                        FragmentBottom currentFragment = (FragmentBottom) getFragmentManager().findFragmentByTag("frag_bottom");
                        currentFragment.filtersearch(text);
                        return true;
                    }
                    return false;
                }
            });

            /*ImageButton search = (ImageButton) retView.findViewById(R.id.Search_Button);
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = search_bar.getText().toString();
                    FragmentBottom currentFragment = (FragmentBottom) getFragmentManager().findFragmentByTag("frag_bottom");
                    currentFragment.filtersearch(text);
                }
            });*/



        }

        return retView;
    }
}