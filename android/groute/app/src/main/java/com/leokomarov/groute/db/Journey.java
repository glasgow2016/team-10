package com.leokomarov.groute.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Journey {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("fellowship")
    @Expose
    private Integer fellowship;

    @SerializedName("supervisorPhone")
    @Expose
    private Integer supervisorPhone;

    @SerializedName("startTime")
    @Expose
    private Integer startTime;

    @SerializedName("endTime")
    @Expose
    private Integer endTime;

    @SerializedName("startLatitude")
    @Expose
    private Double startLatitude;

    @SerializedName("endLatitude")
    @Expose
    private Double endLatitude;

    @SerializedName("startLongitude")
    @Expose
    private Double startLongitude;

    @SerializedName("endLongitude")
    @Expose
    private Double endLongitude;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The fellowship
     */
    public Integer getFellowship() {
        return fellowship;
    }

    /**
     *
     * @param fellowship
     * The fellowship
     */
    public void setFellowship(Integer fellowship) {
        this.fellowship = fellowship;
    }

    /**
     *
     * @return
     * The supervisorPhone
     */
    public Integer getSupervisorPhone() {
        return supervisorPhone;
    }

    /**
     *
     * @param supervisorPhone
     * The supervisorPhone
     */
    public void setSupervisorPhone(Integer supervisorPhone) {
        this.supervisorPhone = supervisorPhone;
    }

    /**
     *
     * @return
     * The startTime
     */
    public Integer getStartTime() {
        return startTime;
    }

    /**
     *
     * @param startTime
     * The startTime
     */
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @return
     * The endTime
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     *
     * @param endTime
     * The endTime
     */
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    /**
     *
     * @return
     * The startLatitude
     */
    public Double getStartLatitude() {
        return startLatitude;
    }

    /**
     *
     * @param startLatitude
     * The startLatitude
     */
    public void setStartLatitude(Double startLatitude) {
        this.startLatitude = startLatitude;
    }

    /**
     *
     * @return
     * The endLatitude
     */
    public Double getEndLatitude() {
        return endLatitude;
    }

    /**
     *
     * @param endLatitude
     * The endLatitude
     */
    public void setEndLatitude(Double endLatitude) {
        this.endLatitude = endLatitude;
    }

    /**
     *
     * @return
     * The startLongitude
     */
    public Double getStartLongitude() {
        return startLongitude;
    }

    /**
     *
     * @param startLongitude
     * The startLongitude
     */
    public void setStartLongitude(Double startLongitude) {
        this.startLongitude = startLongitude;
    }

    /**
     *
     * @return
     * The endLongitude
     */
    public Double getEndLongitude() {
        return endLongitude;
    }

    /**
     *
     * @param endLongitude
     * The endLongitude
     */
    public void setEndLongitude(Double endLongitude) {
        this.endLongitude = endLongitude;
    }

}