

// Ephraim Kanyandula (15328)

package com.ephraim.me.dublinbike;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class listData extends AppCompatActivity {

    public static String [] stations;
    //Double[] position;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        listView = (ListView) findViewById(R.id.resultListView);

        fetchData process = new fetchData();
        process.execute();
    }

    public class fetchData extends AsyncTask<Void,Void,Void> {
        String data = "";
        String dataParsed ="";
        String singleParsed ="";
        @Override
        protected Void doInBackground(Void... voids) {

              // trying to catch the jason for Dublin Bike
            try {
                URL url= new URL("https://api.jcdecaux.com/vls/v1/stations?contract=Dublin&apiKey=711f1aacd608731647100ceddf84c27718c7a92b");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line ="";
                while( line != null){
                    line =bufferedReader.readLine();
                    data = data + line;

                }

               // parsing Jason to get details from jason of dublin bikes
                JSONArray JA = new JSONArray(data);
                stations = new String[JA.length()];
                //position = new Double[JA.length()];
                for (int i=0 ;i <JA.length(); i++){
                    JSONObject JO = (JSONObject) JA.get(i);
                    JSONObject JU = (JSONObject) JO.get("position");

                    JSONObject lat= new JSONObject();
                    JSONObject lng= new JSONObject();







                    singleParsed = "Number:" + JO.get("number") + "\n"+
                            "Name:" + JO.get("name") + "\n"+
                            "Address:" + JO.get("address") + "\n"+

                            "Stand:" + JO.get("bike_stands") + "\n"+
                            "Available:" + JO.get("available_bike_stands") + "\n"+
                            "Bikes:" + JO.get("available_bikes") + "\n"+


                            "Position:" + JO.get("position") + "\n";





                    dataParsed = dataParsed + singleParsed  +"\n";
                    stations[i] = dataParsed;

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
      // creating an adapter to display the data in a listview
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stations);
            Log.d("Testing",  "sta"+stations.length);
            listView.setAdapter(itemsAdapter);




        }
    }}
