package com.example.slide.Rest;

import com.example.slide.model.FilmResult;
import com.example.slide.model.PeopleResult;
import com.example.slide.model.PlanetResult;
import com.example.slide.model.SpecieResult;
import com.example.slide.model.StarshipResult;
import com.example.slide.model.VehicleResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIPlug {
    @GET("films")
    Call<FilmResult> searchFilms(@Query("search") String search);

    @GET("people")
    Call<PeopleResult> searchPeople(@Query("search") String search);

    @GET("planets")
    Call<PlanetResult> searchPlanets(@Query("search") String search);

    @GET("species")
    Call<SpecieResult> searchSpecies(@Query("search") String search);

    @GET("starships")
    Call<StarshipResult> searchStarships(@Query("search") String search);

    @GET("vehicles")
    Call<VehicleResult> searchVehicles(@Query("search") String search);
}
