package com.ephraim.me.dublinbike;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static String [] stations;

    private GoogleMap mMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    public class fetchData extends AsyncTask<Void,Void,Void> {
        String data = "";
        String dataParsed ="";
        String singleParsed ="";
        JSONArray JA;
        @Override
        protected Void doInBackground(Void... voids) {


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
                Log.d("check", "printing:1 ");

                JA = new JSONArray(data);





            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {

                //position = new Double[JA.length()];
                for (int i=0 ;i <JA.length(); i++){
                    Log.d("check", "printing:2 ");
                    JSONObject JO = (JSONObject) JA.get(i);


                    JSONObject lat= new JSONObject();
                    JSONObject lng= new JSONObject();

                    // String lati;

                    JSONObject JU = (JSONObject) JO.get("position");

                    Log.d("check", "1 lat: "+JU.get("lat"));

                    Log.d("check", "1 lat: "+JU.get("lng"));


                    Double latq = Double.valueOf(JU.get("lat").toString());
                    Double lngq = Double.valueOf(JU.get("lng").toString());
                    LatLng position= new LatLng(latq, lngq);

                    mMap.addMarker(new MarkerOptions().position(position).title(JO.get("name").toString()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        new fetchData().execute();

//
   //    LatLng parnellSquareNorth = new LatLng(  53.353462,  -6.265305);
//        mMap.addMarker(new MarkerOptions().position(parnellSquareNorth).title("PARNELL SQUARE NORTH"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parnellSquareNorth, 20));
//
//       LatLng clonmelStreet = new LatLng(  53.353462,   -6.26298);
//        mMap.addMarker(new MarkerOptions().position(clonmelStreet).title("CLONMEL STREET"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clonmelStreet, 20));
//
//        LatLng christchurchPlace = new LatLng(  53.353462,  -6.27012);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(christchurchPlace, 20));
//
//        LatLng granthamStreet = new LatLng(  53.353462,   -6.265436);
//       mMap.addMarker(new MarkerOptions().position(granthamStreet).title("GRANTHAM STREET"));
//       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(granthamStreet, 20));
//
//        LatLng pearseStreet = new LatLng(  53.353462,   -6.250427);
//        mMap.addMarker(new MarkerOptions().position(pearseStreet).title("PEARSE STREET"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pearseStreet, 20));
//



        //for (int i = 0; i <listData.stations.length ;  i++){

           //double lat = Double.perseDouble(listData.getInstance () .getArrayList().get(i).getLat());
           // double lng = Double.perseDouble(listData.getInstance () .getArrayList().get(i).getLng());

            //LatLng stations = new LatLng(listData.dataParsed.lat.getJsonString("Position").get("lat").get(i), listData.dataParsed.lat.getJsonString.get(i) );
           // mMap.addMarker(new MarkerOptions() .position(stations) .title(listData.dataParsed(i)));

        }



    }

