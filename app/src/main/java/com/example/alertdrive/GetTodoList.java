package com.example.alertdrive;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetTodoList extends AsyncTask<Void, Void, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/todos");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();

        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
        }
        return stringBuilder.toString();
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}