package com.example.alertdrive.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import android.app.Fragment;

import com.example.alertdrive.MainActivity;
import com.example.alertdrive.R;

public class FragmentTop extends Fragment {

    EditText search_bar;
    MainActivity activity = (MainActivity)getActivity();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.fragment_top, container);

        search_bar = (EditText)retView.findViewById(R.id.Search_Bar);

            Button androidButton = (Button)retView.findViewById(R.id.All_List);
            androidButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.page =0;
                    FragmentBottom currentFragment = (FragmentBottom) getFragmentManager().findFragmentByTag("frag_bottom");
                    currentFragment.filterall();
                }
            });
            Button iosButton = (Button)retView.findViewById(R.id.Complete_List);
            iosButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.page =0;
                    FragmentBottom currentFragment = (FragmentBottom) getFragmentManager().findFragmentByTag("frag_bottom");
                    currentFragment.filtercompleted();
                }
            });
            Button windowsButton = (Button)retView.findViewById(R.id.Incomplete_List);
            windowsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.page =0;
                    FragmentBottom currentFragment = (FragmentBottom) getFragmentManager().findFragmentByTag("frag_bottom");
                    currentFragment.filterIncompleted();
                }
            });

            search_bar.setOnKeyListener(new View.OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        activity.page =0;
                        String text = search_bar.getText().toString();
                        FragmentBottom currentFragment = (FragmentBottom) getFragmentManager().findFragmentByTag("frag_bottom");
                        currentFragment.filtersearch(text);
                        return true;
                    }
                    return false;
                }
            });


        return retView;
    }
}