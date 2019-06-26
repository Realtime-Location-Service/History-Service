package com.rls.hs.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class RabbitLocationData {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("client_timestamp_utc")
    private long clientTimestampUtc;

    @JsonProperty("server_timestamp_utc")
    private long serverTimestampUtc;

    @JsonProperty("lat")
    private Double latitude;

    @JsonProperty("lon")
    private Double longitude;

    @JsonProperty("domain")
    private String domain;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getClientTimestampUtc() {
        return clientTimestampUtc;
    }

    public void setClientTimestampUtc(long clientTimestampUtc) {
        this.clientTimestampUtc = clientTimestampUtc;
    }

    public long getServerTimestampUtc() {
        return serverTimestampUtc;
    }

    public void setServerTimestampUtc(long serverTimestampUtc) {
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "RabbitLocationData{" +
                "userId='" + userId + '\'' +
                ", clientTimestampUtc=" + clientTimestampUtc +
                ", serverTimestampUtc=" + serverTimestampUtc +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", domain='" + domain + '\'' +
                '}';
    }
}
