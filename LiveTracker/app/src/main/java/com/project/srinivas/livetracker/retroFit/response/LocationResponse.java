package com.project.srinivas.livetracker.retroFit.response;

/**
 * Created by srinivas on 13-12-2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationResponse {

    @SerializedName("activationId")
    @Expose
    private String activationId;

    /**
     *
     * @return
     * The activationId
     */
    public String getActivationId() {
        return activationId;
    }

    /**
     *
     * @param activationId
     * The activationId
     */
    public void setActivationId(String activationId) {
        this.activationId = activationId;
    }

}