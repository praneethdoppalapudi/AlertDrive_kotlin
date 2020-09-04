package com.example.alertdrive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Custom_Adapter extends ArrayAdapter<String> {

    Context mContext;
    List<String> todo = new ArrayList<>();
    List<Boolean> compl = new ArrayList<>();


    // View lookup cache
    private static class ViewHolder {
        ExpandableTextView txtName;
        ImageView txtType;

    }

    public Custom_Adapter(List<String> todo, Context context, List<Boolean> compl) {
        super(context, R.layout.list_row, todo);
        this.todo = todo;
        this.compl = compl;
        this.mContext=context;

    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_row, parent, false);
            viewHolder.txtName = (ExpandableTextView) convertView.findViewById(R.id.one);
            viewHolder.txtType = convertView.findViewById(R.id.text1);



            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.txtName.setText(todo.get(position));
        if(compl.get(position)) {
            viewHolder.txtType.setVisibility(View.VISIBLE);
        }else{
            viewHolder.txtType.setVisibility(View.INVISIBLE);
        }

        // Return the completed view to render on screen
        return convertView;
    }

    public void update(List<String> list, List<Boolean> checkList){
        todo.clear();
        compl.clear();
        todo.addAll(list);
        compl.addAll(checkList);
        this.notifyDataSetChanged();
    }
}