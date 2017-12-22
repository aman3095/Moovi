package aman.com.moovi;

import android.app.Application;

import aman.com.moovi.data.remote.ProgramService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amanpreetsingh on 12/22/17.
 */

public class MooviApplication extends Application {

    private static ProgramService mProgramService;

    public static ProgramService getProgramService() {
        if (mProgramService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Utils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mProgramService = retrofit.create(ProgramService.class);
            return mProgramService;
        } else {
            return mProgramService;
        }
    }

}
