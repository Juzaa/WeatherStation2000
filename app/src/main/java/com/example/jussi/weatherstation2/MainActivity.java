package com.example.jussi.weatherstation2;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import org.codeandmagic.android.gauge.GaugeView;


public class MainActivity extends AppCompatActivity {
    public static TextView humiData;
    public static TextView tempData;
    public static GaugeView mGaugeView1;
    public static GaugeView mGaugeView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGaugeView1 = (GaugeView) findViewById(R.id.gauge_view1);
        mGaugeView2 = (GaugeView) findViewById(R.id.gauge_view2);
        humiData = (TextView) findViewById(R.id.humiData);
        tempData = (TextView) findViewById(R.id.tempData);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                new fetchData().execute();
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);

    }



}
