package com.rls.hs.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LocationPoint {
    private String myDateFormat = "yyyy-MM-dd HH:mm:ss";
    private SimpleDateFormat myFormatter = new SimpleDateFormat(this.myDateFormat);

    @JsonProperty("client_timestamp_utc")
    private String clientTimestampUtc;

    @JsonProperty("server_timestamp_utc")
    private String serverTimestampUtc;

    @JsonProperty("lat")
    private Double latitude;

    @JsonProperty("lon")
    private Double longitude;

    public LocationPoint() {}

    public LocationPoint(DBLocationData theDBLocationData) {
        this.clientTimestampUtc = this.myFormatter.format(theDBLocationData.getClientTimestampUtc());
        this.serverTimestampUtc = this.myFormatter.format(theDBLocationData.getServerTimestampUtc());
        this.latitude = theDBLocationData.getLatitude();
        this.longitude = theDBLocationData.getLongitude();
    }

    public String getClientTimestampUtc() {
        return clientTimestampUtc;
    }

    public void setClientTimestampUtc(String clientTimestampUtc) {
        this.clientTimestampUtc = clientTimestampUtc;
    }

    public String getServerTimestampUtc() {
        return serverTimestampUtc;
    }

    public void setServerTimestampUtc(String serverTimestampUtc) {
        this.serverTimestampUtc = serverTimestampUtc;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LocationPoint{" +
                "clientTimestampUtc='" + clientTimestampUtc + '\'' +
                ", serverTimestampUtc='" + serverTimestampUtc + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
