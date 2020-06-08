package com.lelele.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IndiaStatistics extends AppCompatActivity {

    TextView tvDataDate, tvDailyConfirmed, tvDailyDeceased, tvDailyRecovered, tvTotalConfirmed, tvTotalDeceased, tvTotalRecovered;

    private void fetchData() {

        String url = "https://api.covid19india.org/data.json";
        StringRequest request
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        try {
                            JSONObject data = new JSONObject(response.toString());
                            JSONArray cases_time_series = data.getJSONArray("cases_time_series");
                            JSONObject latest_data = cases_time_series.getJSONObject(cases_time_series.length() - 1);

//                            {
//                                "dailyconfirmed": "10883",
//                                    "dailydeceased": "261",
//                                    "dailyrecovered": "5191",
//                                    "date": "07 June ",
//                                    "totalconfirmed": "257486",
//                                    "totaldeceased": "7206",
//                                    "totalrecovered": "123848"
//                            }

                            tvDataDate.setText(latest_data.getString("date"));
                            tvDailyConfirmed.setText(latest_data.getString("dailyconfirmed"));
                            tvDailyDeceased.setText(latest_data.getString("dailydeceased"));
                            tvDailyRecovered.setText(latest_data.getString("dailyrecovered"));
                            tvTotalConfirmed.setText(latest_data.getString("totalconfirmed"));
                            tvTotalDeceased.setText(latest_data.getString("totaldeceased"));
                            tvTotalRecovered.setText(latest_data.getString("totalrecovered"));

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(IndiaStatistics.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india_statistics);

        tvDataDate = findViewById(R.id.tvDataDate);
        tvDailyConfirmed = findViewById(R.id.tvDailyConfirmed);
        tvDailyDeceased = findViewById(R.id.tvDailyDeceased);
        tvDailyRecovered = findViewById(R.id.tvDailyRecovered);
        tvTotalConfirmed = findViewById(R.id.tvTotalConfirmed);
        tvTotalDeceased = findViewById(R.id.tvTotalDeceased);
        tvTotalRecovered = findViewById(R.id.tvTotalRecovered);

        fetchData();
    }
}