package com.example.ad36_nguyenthanhlong_day06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String json = "{\"coord\": { \"lon\": 139,\"lat\": 35}, \"weather\": [ { \"id\": 800, \"main\": \"Clear\", \"description\": \"clear sky\", \"icon\": \"01n\" } ], \"base\": \"stations\", \"main\": { \"temp\": 289.92, \"pressure\": 1009, \"humidity\": 92, \"temp_min\": 288.71, \"temp_max\": 290.93 }, \"wind\": { \"speed\": 0.47, \"deg\": 107.538 }, \"clouds\": { \"all\": 2 }, \"dt\": 1560350192, \"sys\": { \"type\": 3, \"id\": 2019346, \"message\": 0.0065, \"country\": \"JP\", \"sunrise\": 1560281377, \"sunset\": 1560333478 }, \"timezone\": 32400, \"id\": 1851632, \"name\": \"Shuzenji\", \"cod\": 200 }";
    TextView tvNamecity, tvDescription, tvTemp;
    TextView tvhumidity, tvTempmax, tvTempMin,tvPressure;

    String weather, main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNamecity = findViewById(R.id.tvNamecity);
        tvDescription = findViewById(R.id.tvDescriptin);
        tvTemp = findViewById(R.id.tvTemp);
        tvhumidity = findViewById(R.id.tvHumidity);
        tvTempMin = findViewById(R.id.tvTempMin);
        tvTempmax = findViewById(R.id.tvTempMax);
        tvPressure = findViewById(R.id.tvPressure);

        try {
            JSONObject ob = new JSONObject(json);

            weather = ob.getString("weather");
            main = ob.getString("main");

            String nameCity = ob.getString("name");
            tvNamecity.setText(nameCity);



        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONArray array = new JSONArray(weather);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);

                String id = object.getString("id");
                String main1 = object.getString("main");
                String description = object.getString("description");
                String icon = object.getString("icon");

                tvDescription.setText(description);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONObject ob = new JSONObject(main);

            String temp = ob.getString("temp");
            String presure = ob.getString("pressure");
            String humidity = ob.getString("humidity");
            String temp_min = ob.getString("temp_min");
            String temp_max = ob.getString("temp_max");


            int tempc = MakeRound(temp);
            int tempMax = MakeRound(temp_max);
            int tempMin = MakeRound(temp_min);

             tvTemp.setText(String.valueOf(tempc)+"°C");
             tvhumidity.setText("Humidity \n"+humidity+"%");
             tvTempMin.setText("Min \n"+tempMin+"°C");
             tvTempmax.setText("Max \n"+tempMax+"°C");
             tvPressure.setText("Pressure \n"+presure+" hPa");


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public static int MakeRound(String strnum){
        int num = (int) ((Float.valueOf(strnum)-273)+0.5);
        return  num;
    }
}
