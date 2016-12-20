package com.project.srinivas.livetracker.retroFit.services;

import com.project.srinivas.livetracker.retroFit.LocRequest;
import com.project.srinivas.livetracker.retroFit.response.LocationResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by srinivas on 13-12-2016.
 */

public interface TrackingService {

    @POST("Hello%20World%20With%20Params")
    @Headers({
            //"Content-Type : appliction/json",
            //"Accept : appliction/json",
            "Authorization : Basic NTE1ZDQzMzgtYTFiOC00YWFiLWFjYWUtNGY2YTBhNWE4NDI3OlFWN1FHcnhRYXBtbVFrYWV1TWxIM3MxSktlNUgwNE01aHBlbGJCdHFZZmxxaUlHSmsya0VIRDRtSU9uWFNoZmE="})

    Call<LocationResponse> sendLocation(
            @Body LocRequest data
          );
}