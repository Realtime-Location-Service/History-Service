package com.rls.hs.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class LocationDataResponse {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("paths")
    private List<LocationPoint> paths;

    public LocationDataResponse() {}

    public LocationDataResponse(List<DBLocationData> someDBLocationData) {
        if (someDBLocationData.size() == 0) {
            return ;
        }
        this.userId = someDBLocationData.get(0).getUserId();
        this.domain = someDBLocationData.get(0).getDomain();

        this.paths = someDBLocationData.stream().map(l -> new LocationPoint(l)).collect(Collectors.toList());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<LocationPoint> getPaths() {
        return paths;
    }

    public void setPaths(List<LocationPoint> paths) {
        this.paths = paths;
    }

    @Override
    public String toString() {
        return "LocationDataResponse{" +
                "userId='" + userId + '\'' +
                ", domain='" + domain + '\'' +
                ", paths=" + paths +
                '}';
    }
}
