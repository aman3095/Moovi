package aman.com.moovi.data.remote;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

import aman.com.moovi.model.Program;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by amanpreetsingh on 12/21/17.
 */

public interface ProgramService {

    @GET("discover/{programType}?sort_by=popularity.desc")
    Call<JsonObject> getPopularProgramsCall(
            @Path("programType") String programType,
            @Query("api_key") String api_key );

    @GET("{programType}/{id}")
    Call<JsonObject> getProgramCall(
            @Path("programType") String programType,
            @Path("id") int id,
            @Query("api_key") String api_key);
}
