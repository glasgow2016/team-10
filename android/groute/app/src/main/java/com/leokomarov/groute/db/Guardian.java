package com.leokomarov.groute.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Guardian {

    @SerializedName("phone")
    @Expose
    private Integer phone;

    @SerializedName("fname")
    @Expose
    private String fname;

    @SerializedName("sname")
    @Expose
    private String sname;

    /**
     *
     * @return
     * The phone
     */
    public Integer getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(Integer phone) {
        this.phone = phone;
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

}