package com.example.alertdrive.Fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.app.Fragment
import android.widget.Toast

import com.example.alertdrive.Custom_Adapter
import com.example.alertdrive.GetTodoList
import com.example.alertdrive.MainActivity
import com.example.alertdrive.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList
import java.util.HashSet
import java.util.concurrent.ExecutionException

class FragmentBottom : Fragment() {



    internal var todo: MutableList<String> = ArrayList()
    internal var compl: MutableList<Boolean> = ArrayList()
    internal  var todos: JSONArray? = null
    internal var alltodos: MutableList<JSONObject> = ArrayList()
    internal var dynTodos: MutableList<JSONObject> = ArrayList()
    internal var compltodos: MutableList<JSONObject> = ArrayList()
    internal var incompltodos: MutableList<JSONObject> = ArrayList()
    internal var uniqueEntries: MutableSet<JSONObject> = HashSet()
    internal  var exAdapter2: Custom_Adapter? = null
    internal var asyncTask = GetTodoList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val context = activity as MainActivity
        var strtext = ""

        val retView = inflater.inflate(R.layout.fragment_bottom, container)
        val listView = retView!!.findViewById<ListView>(android.R.id.list)
        val todo = ArrayList<String>()
        val compl = ArrayList<Boolean>()
        val asyncTask = GetTodoList()

        try {
            strtext = asyncTask.execute().get()
            todos = JSONArray(strtext)
            for (i in 0 until todos!!.length()) {
                val c = todos!!.getJSONObject(i)
                alltodos.add(c)
                val id = c.getBoolean("completed")
                if (id) {
                    compltodos.add(c)
                } else {
                    incompltodos.add(c)
                }
            }
            dynTodos = alltodos
            for (i in 0..4) {
                val c = dynTodos[i]
                todo.add(c.getString("title"))
                compl.add(c.getBoolean("completed"))


            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                exAdapter2 = Custom_Adapter(todo, getContext(), compl)
            }

            listView.adapter = exAdapter2
            if (retView != null) {
            }

        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        if (retView != null) {
        }
        return retView
    }

    fun update(page: Int) {
        try {
            todo.clear()
            compl.clear()
            if (page + 5 < dynTodos.size) {
                for (i in page until page + 5) {
                    val c = dynTodos[i]
                    todo.add(c.getString("title"))
                    compl.add(c.getBoolean("completed"))
                }
                exAdapter2!!.update(todo, compl)
            } else {
                Toast.makeText(activity, "REACHED END", Toast.LENGTH_SHORT).show()
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    fun filterall() {
        retrieveDaTA()
        updateFunctionality(alltodos)
    }

    fun filtercompleted() {
        retrieveDaTA()
        updateFunctionality(compltodos)
    }

    fun filterIncompleted() {
        retrieveDaTA()
        updateFunctionality(incompltodos)
    }

    fun filtersearch(text: String) {
        val words = text.split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        try {
            uniqueEntries.clear()
            for (i in alltodos.indices) {
                val c = alltodos[i]
                val sentence = c.getString("title")
                for (word in words) {
                    if (sentence.contains(word)) {
                        uniqueEntries.add(c)
                    }
                }
            }
            dynTodos.clear()
            if (uniqueEntries.size == 0) {
                Toast.makeText(activity, "TODO NOT FOUND", Toast.LENGTH_SHORT).show()
            } else {
                for (i in uniqueEntries) {
                    dynTodos.add(i)
                }
            }


            updateFunctionality(dynTodos)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    fun updateFunctionality(`val`: List<JSONObject>) {
        try {
            dynTodos = `val` as MutableList<JSONObject>
            todo.clear()
            compl.clear()
            if (dynTodos.size > 0) {
                for (i in 0..4) {
                    val c = dynTodos[i]
                    todo.add(c.getString("title"))
                    compl.add(c.getBoolean("completed"))
                }
            } else {
                retrieveDaTA()
                updateFunctionality(alltodos)
            }
            exAdapter2?.update(todo, compl)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    internal fun retrieveDaTA() {
        try {
            val strtext = GetTodoList().execute().get()
            alltodos.clear()
            compltodos.clear()
            incompltodos.clear()
            todos = JSONArray(strtext)
            for (i in 0 until todos!!.length()) {
                val c = todos!!.getJSONObject(i)
                alltodos.add(c)
                val id = c.getBoolean("completed")
                if (id) {
                    compltodos.add(c)
                } else {
                    incompltodos.add(c)
                }
            }
            dynTodos = alltodos
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }


}
