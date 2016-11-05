package com.leokomarov.groute.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("fname")
    @Expose
    private String fname;

    @SerializedName("sname")
    @Expose
    private String sname;

    @SerializedName("guardianPhone")
    @Expose
    private Integer guardianPhone;

    @SerializedName("fellowship")
    @Expose
    private Integer fellowship;

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
     * The fname
     */
    public String getFname() {
        return fname;
    }

    /**
     *
     * @param fname
     * The fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     *
     * @return
     * The sname
     */
    public String getSname() {
        return sname;
    }

    /**
     *
     * @param sname
     * The sname
     */
    public void setSname(String sname) {
        this.sname = sname;
    }

    /**
     *
     * @return
     * The guardianPhone
     */
    public Integer getGuardianPhone() {
        return guardianPhone;
    }

    /**
     *
     * @param guardianPhone
     * The guardianPhone
     */
    public void setGuardianPhone(Integer guardianPhone) {
        this.guardianPhone = guardianPhone;
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

}