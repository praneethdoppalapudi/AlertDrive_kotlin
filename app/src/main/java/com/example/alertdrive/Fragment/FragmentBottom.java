package com.example.alertdrive.Fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import android.app.Fragment;

import com.example.alertdrive.Custom_Adapter;
import com.example.alertdrive.GetTodoList;
import com.example.alertdrive.MainActivity;
import com.example.alertdrive.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class FragmentBottom extends Fragment {


    MainActivity context;

    List<String> todo = new ArrayList<>();
    List<Boolean> compl = new ArrayList<>();
    JSONArray todos;
    List<JSONObject> alltodos = new ArrayList<JSONObject>();
    List<JSONObject> dynTodos = new ArrayList<JSONObject>();
    List<JSONObject> compltodos = new ArrayList<JSONObject>();
    List<JSONObject> incompltodos = new ArrayList<JSONObject>();
    Set<JSONObject> uniqueEntries = new HashSet<JSONObject>();
    Custom_Adapter exAdapter2;
    // This method will be invoked when the Fragment view object is created.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = (MainActivity) getActivity();
        String strtext = "";
        String title = "";

        View retView = inflater.inflate(R.layout.fragment_bottom, container);
        ListView listView =  retView.findViewById(android.R.id.list);
        List<String> todo = new ArrayList<>();
        List<Boolean> compl = new ArrayList<>();


        GetTodoList asyncTask=new GetTodoList();
        try {
            strtext = asyncTask.execute().get();


            // Getting JSON Array node
            todos = new JSONArray(strtext);
            int temp=0;
            int temp1=0;
            for (int i = 0; i < todos.length(); ++i) {
                JSONObject c = todos.getJSONObject(i);
                alltodos.add(c);
                boolean id = c.getBoolean("completed");
                if (id) {
                    compltodos.add(c);
                }else {
                    incompltodos.add(c);
                }
            }
            dynTodos = alltodos;

            // looping through All Contacts
            for (int i = 0; i < 5; i++) {
                JSONObject c = dynTodos.get(i);
                todo.add(c.getString("title"));
                compl.add(c.getBoolean("completed"));


            }
            exAdapter2 = new Custom_Adapter(todo, getContext(), compl);

            listView.setAdapter(exAdapter2);
            if(retView!=null) {
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(retView!=null) {
        }
        return retView;
    }

    public void update(int page){
        try{
            todo.clear();
            compl.clear();
            for (int i = page; i < page+5; i++) {
                JSONObject c = dynTodos.get(i);
                todo.add(c.getString("title"));
                compl.add(c.getBoolean("completed"));
            }
            exAdapter2.update(todo,compl);
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    public void filterall(){
        try{
            dynTodos = alltodos;
            todo.clear();
            compl.clear();
            for (int i = 0; i < 5; i++) {
                JSONObject c = dynTodos.get(i);
                todo.add(c.getString("title"));
                compl.add(c.getBoolean("completed"));
            }
            exAdapter2.update(todo,compl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void filtercompleted(){
        try{
            dynTodos = compltodos;
            todo.clear();
            compl.clear();
            for (int i = 0; i < 5; ++i) {
                JSONObject c = dynTodos.get(i);
                todo.add(c.getString("title"));
                compl.add(c.getBoolean("completed"));
            }
            exAdapter2.update(todo,compl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void filterIncompleted(){
        try{
            dynTodos = incompltodos;
            todo.clear();
            compl.clear();
            for (int i = 0; i < 5; ++i) {
                JSONObject c = dynTodos.get(i);
                todo.add(c.getString("title"));
                compl.add(c.getBoolean("completed"));
            }
            exAdapter2.update(todo,compl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void filtersearch(String text){
        String[] words = text.split(" ");
        try{

            for (int i = 0; i < alltodos.size(); ++i) {
                JSONObject c = alltodos.get(i);
                String sentence = c.getString("title");
                for(String word : words){
                    if (sentence.contains(word)) {
                        uniqueEntries.add(c);
                    }
                }
            }
            dynTodos.clear();
            for(JSONObject i : uniqueEntries) {
                dynTodos.add(i);
            }

            todo.clear();
            compl.clear();
            for (int i = 0; i < 5; ++i) {
                JSONObject c = dynTodos.get(i);
                todo.add(c.getString("title"));
                compl.add(c.getBoolean("completed"));
            }
            exAdapter2.update(todo,compl);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
