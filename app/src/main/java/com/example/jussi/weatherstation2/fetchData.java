package com.example.jussi.weatherstation2;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jussi on 13/11/2017.
 */

public class fetchData extends AsyncTask<Void, Void, Void> {
    String data = "";
    String fetchTemp = "";
    String humiParse = "";
    String tempParse = "";
    String fetchHumi = "";
    float tempFloat = 0;
    float humiFloat = 0;

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url = new URL("http://192.168.43.183/temp.php");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

                JSONObject JO = new JSONObject(data);

                humiParse = "Humidity: " + JO.getString("humidity") + "%";
                tempParse = "Temperature: " + JO.getString("temp") + "\u00b0" +"C";
                fetchTemp = JO.getString("temp");
                fetchHumi = JO.getString("humidity");
                tempFloat = Float.parseFloat(fetchTemp);
                humiFloat = Float.parseFloat(fetchHumi);

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
        MainActivity.mGaugeView1.setTargetValue(tempFloat);
        MainActivity.mGaugeView2.setTargetValue(humiFloat);
        MainActivity.humiData.setText(this.humiParse);
        MainActivity.tempData.setText(this.tempParse);
    }
}
