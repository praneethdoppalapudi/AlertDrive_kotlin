package com.example.alertdrive

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import java.util.ArrayList

class Custom_Adapter(todo: MutableList<String>, private val mContext: Context, compl: MutableList<Boolean>) : ArrayAdapter<String>(mContext, R.layout.list_row, todo) {
    private var todo = ArrayList<String>()
    private var compl = ArrayList<Boolean>()


    private class ViewHolder {
        internal var txtName: ExpandableTextView? = null
        internal var txtType: ImageView? = null
    }

    init {
        this.todo = todo as ArrayList<String>
        this.compl = compl as ArrayList<Boolean>
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val viewHolder: ViewHolder


        if (convertView == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(context)
            convertView = inflater.inflate(R.layout.list_row, parent, false)
            viewHolder.txtName = convertView!!.findViewById<View>(R.id.one) as ExpandableTextView
            viewHolder.txtType = convertView.findViewById(R.id.text1)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder.txtName!!.text = todo[position]
        if (compl[position]) {
            viewHolder.txtType!!.visibility = View.VISIBLE
        } else {
            viewHolder.txtType!!.visibility = View.INVISIBLE
        }
        return convertView
    }

    fun update(list: List<String>, checkList: List<Boolean>) {
        todo.clear()
        compl.clear()
        todo.addAll(list)
        compl.addAll(checkList)
        this.notifyDataSetChanged()
    }
}