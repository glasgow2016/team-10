package com.leokomarov.groute.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fellowship {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("parents-phone-number")
    @Expose
    private Integer parentsPhoneNumber;

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
     * The parentsPhoneNumber
     */
    public Integer getParentsPhoneNumber() {
        return parentsPhoneNumber;
    }

    /**
     *
     * @param parentsPhoneNumber
     * The parents-phone-number
     */
    public void setParentsPhoneNumber(Integer parentsPhoneNumber) {
        this.parentsPhoneNumber = parentsPhoneNumber;
    }

}