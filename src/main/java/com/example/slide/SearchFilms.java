package com.example.slide;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.slide.Rest.APIClient;
import com.example.slide.model.FilmResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFilms extends AppCompatActivity {

    EditText edSearch;
    TextView txtResult;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_films);

        edSearch = (EditText) findViewById(R.id.fedSearch);
        txtResult = (TextView) findViewById(R.id.ftxtResult);
        btnSearch = (Button) findViewById(R.id.btnfSearch);
    }

    public void btnSearch_onClick(View v)
    {

        searchPeople(edSearch.getText().toString());
    }

    private void searchPeople(String tagword)
    {

        Call<FilmResult> call = APIClient.get().searchFilms(tagword);

        call.enqueue(new Callback<FilmResult>()
        {
            @Override
            public void onFailure(Call<FilmResult> call, Throwable t)
            {
                txtResult.setText("An error ocurred: " + t.toString());
                edSearch.setText("");
            }

            @Override
            public void onResponse(Call<FilmResult> call, Response<FilmResult> response)
            {


                Log.d("APIPlug", "Successfully response fetched" );
                edSearch.setText("");
                FilmResult films = response.body();

                if(films.results.size() > 0)
                    txtResult.setText(films.results.get(0).toString());
                else
                    txtResult.setText("Your request was not found!");

            }
        });
    }
}