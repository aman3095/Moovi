package aman.com.moovi.ui.main;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import aman.com.moovi.MooviApplication;
import aman.com.moovi.Utils;
import aman.com.moovi.data.remote.ProgramService;
import aman.com.moovi.model.JsonParent;
import aman.com.moovi.model.Movie;
import aman.com.moovi.model.Program;
import aman.com.moovi.model.TVShow;
import aman.com.moovi.ui.base.BasePresenter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amanpreetsingh on 12/21/17.
 */

public class ProgramListPresenter extends BasePresenter<ProgramListView> {

    private final String TAG = "ProgramListPresenter";

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    Retrofit retrofit;

    ProgramService mProgramService = MooviApplication.getProgramService();

    Gson mGson = new Gson();

    public ProgramListPresenter() {
    }

    public void loadPrograms(final String programType) {
        checkViewAttached();
        mProgramService.getPopularProgramsCall(programType, Utils.API_KEY).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (programType.equals(Utils.MOVIE)) {
                        Type complexType = new TypeToken<JsonParent<Movie>>() {}.getType();
                        JsonParent<Movie> jsonParent = mGson.fromJson(response.body().toString(), complexType);
                        getmvpView().showProgramList(jsonParent.getResults());
                    } else if (programType.equals(Utils.TV_SHOW)) {
                        Type complexType = new TypeToken<JsonParent<TVShow>>() {}.getType();
                        JsonParent<TVShow> jsonParent = mGson.fromJson(response.body().toString(), complexType);
                        getmvpView().showProgramList(jsonParent.getResults());
                    }
                } else {
                    getmvpView().showError();
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                getmvpView().showError();
            }
        });
    }

}
