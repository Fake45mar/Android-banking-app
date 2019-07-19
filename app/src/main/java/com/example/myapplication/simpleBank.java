package com.example.myapplication;
import org.json.JSONObject;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class simpleBank {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private String date = sdf.format(new Date());
    private final String USER_AGENT = "Mozilla/5.0";
    private URL obj;
    private HttpURLConnection con;
    private HttpsURLConnection conn;
    private BufferedReader requestResponse;
    private DataOutputStream wr;
    private JSONObject jsonCrpito;
    private JSONObject currency;
    private JSONObject USDcurrency;
    private JSONObject EURcurrency;
    private JSONObject GBPcurrency;
    private JSONObject jsonCurseGBP;
    private JSONObject jsonCurseUSD;
    private JSONObject jsonCurseEUR;
    private String GBP;
    private String EUR;
    private String USD;

    public String reqestCrpitoCurrency() throws Exception{
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        obj = new URL(url);
        con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("Response code " + responseCode);
        requestResponse = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuffer response = new StringBuffer();
        String responseString;
        while((responseString = requestResponse.readLine()) != null){
            response.append(responseString);
        }
        jsonCrpito = new JSONObject(response.toString());
        currency = new JSONObject(jsonCrpito.get("bpi").toString());
        USDcurrency = new JSONObject(currency.get("USD").toString());
        USD = USDcurrency.get("rate").toString();
        EURcurrency = new JSONObject(currency.get("EUR").toString());
        EUR = EURcurrency.get("rate").toString();
        GBPcurrency = new JSONObject(currency.get("GBP").toString());
        GBP = GBPcurrency.get("rate").toString();
        USD = USD.substring(0, USD.length() - 5);
        return (USD + " USD");
    }

    public String getCurrencyEUR() throws Exception{
        String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?" + "valcode=EUR" + "&date=" + date + "&json";
        System.out.println(url);
        obj = new URL(url);
        conn = (HttpsURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = conn.getResponseCode();
        System.out.println(responseCode);
        requestResponse = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer response = new StringBuffer();
        String input;
        while((input = requestResponse.readLine()) != null){
            response.append(input);
        }
        response.deleteCharAt(0);
        response.deleteCharAt(response.length() - 1);
        jsonCurseEUR = new JSONObject(response.toString());
        return (jsonCurseEUR.get("rate").toString().substring(0, jsonCurseEUR.get("rate").toString().length() - 4));
//        System.out.println(response.toString());
    }

    public String getCurrencyUSD() throws Exception{
        String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?" + "valcode=USD" + "&date=" + date + "&json";
        System.out.println(url);
        obj = new URL(url);
        conn = (HttpsURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("Response code " + responseCode);
        requestResponse = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer response = new StringBuffer();
        String input;
        while((input = requestResponse.readLine()) != null){
            response.append(input);
        }
        response.deleteCharAt(0);
        response.deleteCharAt(response.length() - 1);
        jsonCurseUSD = new JSONObject(response.toString());
        return(jsonCurseUSD.get("rate").toString().substring(0, jsonCurseUSD.get("rate").toString().length() - 4));
//        System.out.println(response.toString());
    }

    public String getCurrencyGBP() throws Exception{
        String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?" + "valcode=GBP" + "&date=" + date + "&json";
        System.out.println(url);
        obj = new URL(url);
        conn = (HttpsURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("Response code " + responseCode);
        requestResponse = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer response = new StringBuffer();
        String input;
        while((input = requestResponse.readLine()) != null){
            response.append(input);
        }
        response.deleteCharAt(0);
        response.deleteCharAt(response.length() - 1);
        jsonCurseGBP = new JSONObject(response.toString());
        return (jsonCurseGBP.get("rate").toString().substring(0, jsonCurseGBP.get("rate").toString().length() - 4));
    }
}

