package com.example.sqlite_math_game;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchdata extends AsyncTask<Void,Void,Void> {

    String data = "";
    String dataParsed="";
    String singleParsed="";

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL url = new URL("https://jsonkeeper.com/b/PHSZ");
            //Create a connection first
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //Create input stream to read data
            InputStream inputStream = httpURLConnection.getInputStream();
            //Create buffer reader to read data from input stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                //Read lines
                line = bufferedReader.readLine();
                data = data + line;
            }

            //Parsing data
            JSONArray JA = new JSONArray(data);
            for(int i=0;i< JA.length();i++){
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed = "Question:" + JO.get("Q") + "\n" +
                        "Answer:" + JO.get("answer") + "\n" ;
                dataParsed = dataParsed + singleParsed;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        FetchActivity.textView1.setText(this.dataParsed);
    }
}
