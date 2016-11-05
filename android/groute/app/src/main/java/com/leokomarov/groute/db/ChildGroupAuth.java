package com.leokomarov.groute.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChildGroupAuth {

    @SerializedName("child")
    @Expose
    private Integer child;
    @SerializedName("guardianPhone")
    @Expose
    private Integer guardianPhone;

    /**
     *
     * @return
     * The child
     */
    public Integer getChild() {
        return child;
    }

    /**
     *
     * @param child
     * The child
     */
    public void setChild(Integer child) {
        this.child = child;
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

}