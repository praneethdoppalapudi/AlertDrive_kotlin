package com.example.alertdrive

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class GetTodoList : AsyncTask<Void, Void, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: Void): String {

        val stringBuilder = StringBuilder()
        var line: String = ""
        try {
            val url = URL("https://jsonplaceholder.typicode.com/todos")
            val conn = url.openConnection() as HttpURLConnection
            conn.doInput = true
            conn.connect()

            val bufferedReader = BufferedReader(InputStreamReader(conn.inputStream))
            line = bufferedReader.use(BufferedReader::readText);

            bufferedReader.close()

        } catch (e: IOException) {
            Log.e("PlaceholderFragment", "Error ", e)
        }

        return line
    }


    override fun onPostExecute(s: String) {
        super.onPostExecute(s)
    }
}