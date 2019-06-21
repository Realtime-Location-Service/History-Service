package com.rls.hs.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rls.hs.converters.JSONToMapConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "location_history")
public class LocationData {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    @JsonIgnore
    private int id;

    @JsonProperty("userID")
    @Column(name = "userID")
    @NotNull(message = "userId is required")
    private String userID;

    @JsonProperty("timestamp")
    @Column(name = "timestamp")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "timestamp is required")
    private Timestamp timestamp;

    @JsonProperty("latitude")
    @Column(name = "latitude")
    private Double latitude;

    @JsonProperty("longitude")
    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "info", columnDefinition = "json")
    @Convert(converter = JSONToMapConverter.class)
    private Map<String, Object> info = new HashMap<>();

    @JsonProperty("domain")
    @Column(name = "domain")
    private String domain;

    @Override
    public String toString() {
        return "LocationData{" +
                "id=" + id +
                ", userID='" + userID + '\'' +
                ", timestamp=" + timestamp +
                ", lat=" + latitude +
                ", lon=" + longitude +
                ", info=" + info +
                ", domain='" + domain + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Double getLat() {
        return latitude;
    }

    public void setLat(Double lat) {
        this.latitude = lat;
    }

    public Double getLon() {
        return longitude;
    }

    public void setLon(Double lon) {
        this.longitude = lon;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
