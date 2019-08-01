package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    protected Map<String, String> counts = new HashMap<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.main);
        final TextView btc = (TextView) findViewById(R.id.courseBtc);
        final TextView usd = (TextView) findViewById(R.id.courseUsd);
        final TextView eur = (TextView) findViewById(R.id.courseEur);
        final TextView gbp = (TextView) findViewById(R.id.courseGbp);
        Button upd = (Button) findViewById(R.id.updateCourse);
        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    requestBtc req = new requestBtc("Requesting currency");
                    req.start();
                    btc.setText(counts.get("BTC"));
                    usd.setText(counts.get("USD"));
                    eur.setText(counts.get("EUR"));
                    gbp.setText(counts.get("GBP"));
                }
                catch(Exception e){
                    System.out.println(e + "ERROR414");
                }
            }
        });
    }
    class requestBtc extends Thread {
        private Thread t;
        private String threadName;
        public requestBtc(String threadName){
            this.threadName = threadName;
        }
        public void run(){
            try {
                simpleBank request = new simpleBank();
                String btc = request.reqestCrpitoCurrency();
                String usd = request.getCurrencyUSD();
                String eur = request.getCurrencyEUR();
                String gbp = request.getCurrencyGBP();
                counts.clear();
                counts.put("BTC", btc);
                counts.put("USD", usd);
                counts.put("EUR", eur);
                counts.put("GBP", gbp);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        public void start(){
            if (t == null){
                try {
                    t = new Thread(this, threadName);
                    t.start();
                }
                catch(Exception e){
                    t.interrupt();
                    e.printStackTrace();
                }
            }
        }
    }
}
