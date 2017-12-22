package aman.com.moovi.ui.detail;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import aman.com.moovi.MooviApplication;
import aman.com.moovi.Utils;
import aman.com.moovi.data.remote.ProgramService;
import aman.com.moovi.model.DetailObject;
import aman.com.moovi.model.LandingDetailObject;
import aman.com.moovi.model.Movie;
import aman.com.moovi.model.Program;
import aman.com.moovi.model.SummaryDetailObject;
import aman.com.moovi.model.TVShow;
import aman.com.moovi.ui.base.BasePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amanpreetsingh on 12/22/17.
 */

public class DetailPresenter extends BasePresenter<DetailView> {

    ProgramService mProgramService = MooviApplication.getProgramService();

    Gson mGson = new Gson();

    void loadProgramDetail(Program program) {
        if (program.getId() == -1)
            return;
        checkViewAttached();
        final String programType = program.getProgramType();
        mProgramService.getProgramCall(programType, program.getId(), Utils.API_KEY).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    String jsonString = response.body().toString();
                    if (programType.equals(Utils.MOVIE)){
                        Movie movie = mGson.fromJson(jsonString, Movie.class);
                        getmvpView().showProgramDetails(movie);
                    } else if (programType.equals(Utils.TV_SHOW)) {
                        TVShow tvShow = mGson.fromJson(jsonString, TVShow.class);
                        getmvpView().showProgramDetails(tvShow);
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

    void loadBackgroundImage(Program program) {
        checkViewAttached();
        getmvpView().showBackgroundImage(program.getPosterPath());
    }

    ArrayList<DetailObject> getDetailObjects(Program program) {
        ArrayList<DetailObject> list = new ArrayList<>();
        list.add(new LandingDetailObject(
                getTitleYearSpan(program.getTitle(), program.getYear()),
                program instanceof Movie ? ((Movie) program).getVoteAverage() : -1));

        list.add(new SummaryDetailObject(program.getOverview(),
                program instanceof Movie ? ((Movie) program).getReleaseDate() : ((TVShow) program).getFirstAiredDate(),
                program.getGenre()));

        return list;
    }

    private static Spannable getTitleYearSpan(String title, int year) {
        if (year == -1){
            return new SpannableString(title);
        }
        String str = title + " (" + year + ")";
        Spannable span = new SpannableString(str);
        int spanStartIndex = title.length();
        int spanEndIndex = str.length();
        span.setSpan(new RelativeSizeSpan(0.55f), spanStartIndex, spanEndIndex, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        return span;
    }

}
