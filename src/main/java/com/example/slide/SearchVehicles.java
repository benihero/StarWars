package com.example.slide;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.slide.Rest.APIClient;
import com.example.slide.model.VehicleResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchVehicles extends AppCompatActivity {

    EditText edSearch;
    TextView txtResult;
    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_vehicles);

        edSearch = (EditText) findViewById(R.id.edSearch);
        txtResult = (TextView) findViewById(R.id.txtResult);
        btnSearch = (Button) findViewById(R.id.btnSearch);
    }

    public void btnSearch_onClick(View v)
    {

        searchPeople(edSearch.getText().toString());
    }

    private void searchPeople(String tagword)
    {

        Call<VehicleResult> call = APIClient.get().searchVehicles(tagword);

        call.enqueue(new Callback<VehicleResult>()
        {
            @Override
            public void onFailure(Call<VehicleResult> call, Throwable t)
            {
                txtResult.setText("An error ocurred: " + t.toString());
                edSearch.setText("");
            }

            @Override
            public void onResponse(Call<VehicleResult> call, Response<VehicleResult> response)
            {


                Log.d("APIPlug", "Successfully response fetched" );
                edSearch.setText("");
                VehicleResult vehicles = response.body();

                if(vehicles.results.size() > 0)
                    txtResult.setText(vehicles.results.get(0).toString());
                else
                    txtResult.setText("Your request was not found!");

            }
        });
    }


}