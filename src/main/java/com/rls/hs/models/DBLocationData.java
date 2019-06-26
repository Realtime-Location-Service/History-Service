package com.rls.hs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "location_history")
public class DBLocationData {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    @JsonIgnore
    private int id;

    @JsonProperty("user_id")
    @Column(name = "user_id")
    @NotNull(message = "user_id is required")
    private String userId;

    @JsonProperty("client_timestamp_utc")
    @Column(name = "client_timestamp_utc")
    @NotNull(message = "client_timestamp_utc is required")
    private Date clientTimestampUtc;


    @JsonProperty("server_timestamp_utc")
    @Column(name = "server_timestamp_utc")
    @NotNull(message = "server_timestamp_utc is required")
    private Date serverTimestampUtc;

    @JsonProperty("lat")
    @Column(name = "latitude")
    private Double latitude;

    @JsonProperty("lon")
    @Column(name = "longitude")
    private Double longitude;

    @JsonProperty("domain")
    @Column(name = "domain")
    private String domain;

    public DBLocationData(){}

    public DBLocationData(RabbitLocationData theRabbitLocationData) {
        this.userId = theRabbitLocationData.getUserId();
        this.clientTimestampUtc = new Date(theRabbitLocationData.getClientTimestampUtc() * 1000);
        this.serverTimestampUtc = new Date(theRabbitLocationData.getServerTimestampUtc() * 1000);
        this.latitude = theRabbitLocationData.getLatitude();
        this.longitude = theRabbitLocationData.getLongitude();
        this.domain = theRabbitLocationData.getDomain();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getClientTimestampUtc() {
        return clientTimestampUtc;
    }

    public void setClientTimestampUtc(Date clientTimestampUtc) {
        this.clientTimestampUtc = clientTimestampUtc;
    }

    public Date getServerTimestampUtc() {
        return serverTimestampUtc;
    }

    public void setServerTimestampUtc(Date serverTimestampUtc) {
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
        return "DBLocationData{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", clientTimestampUtc=" + clientTimestampUtc +
                ", serverTimestampUtc=" + serverTimestampUtc +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", domain='" + domain + '\'' +
                '}';
    }
}
