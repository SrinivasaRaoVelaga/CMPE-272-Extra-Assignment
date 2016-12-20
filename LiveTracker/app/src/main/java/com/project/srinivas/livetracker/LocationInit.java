package com.project.srinivas.livetracker;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.project.srinivas.livetracker.retroFit.services.TrackingService;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by srinivas on 13-12-2016.
 */

public class LocationInit extends MultiDexApplication {


    private static LocationInit mInstance;
    private static Context mContext;
    private static Context mApplicationContext;
    private Retrofit mRetrofit;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized LocationInit getInstance() {
        if (mInstance == null) {
            mInstance = new LocationInit();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate();

        initializeVariables();
        buildRetrofitClient();
        //FileManager.createAllDirectories(getApplicationContext());
    }
    public TrackingService getLocationServices() {
        return mRetrofit.create(TrackingService.class);
    }

    private void initializeVariables() {
        mInstance = this;
        mApplicationContext = this.getApplicationContext();

    }
    private void buildRetrofitClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .baseUrl("https://openwhisk.ng.bluemix.net/api/v1/namespaces/srinivasarao.velaga%40sjsu.edu_dev/actions/").build();
    }

    public Context getCurrentActivityContext() {
        if (mContext == null) {
            return mApplicationContext;
        } else {
            return mContext;
        }
    }

    public boolean isConnectedToInterNet() {
        ConnectivityManager connectivity = (ConnectivityManager) getCurrentActivityContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
        }
        return false;
    }

}
